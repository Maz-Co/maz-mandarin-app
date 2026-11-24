package com.example.audio

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import com.example.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

data class MandarinVoiceOption(
    val name: String,
    val displayName: String,
    val isHighQuality: Boolean
)

class AudioFeedbackManager(private val context: Context) {

    private var tts: TextToSpeech? = null
    private var isTtsReady = false
    private var mediaPlayer: MediaPlayer? = null
    private val mainHandler = Handler(Looper.getMainLooper())
    private var stopRunnable: Runnable? = null

    private var currentSpeechRate: Float = 0.88f
    private var preferredVoiceName: String = ""

    private val _availableVoices = MutableStateFlow<List<MandarinVoiceOption>>(emptyList())
    val availableVoices: StateFlow<List<MandarinVoiceOption>> = _availableVoices.asStateFlow()

    init {
        tts = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                isTtsReady = true
                tts?.setLanguage(Locale.SIMPLIFIED_CHINESE)
                refreshMandarinVoices()
            }
        }
    }

    private fun refreshMandarinVoices() {
        val engine = tts ?: return
        val voices: Set<Voice> = try {
            engine.voices.orEmpty()
        } catch (_: Exception) {
            emptySet()
        }

        val zhVoices = voices
            .filter { v ->
                val lang = v.locale?.language?.lowercase(Locale.ROOT).orEmpty()
                val tag = v.locale?.toLanguageTag()?.lowercase(Locale.ROOT).orEmpty()
                lang == "zh" || tag.startsWith("zh") || tag.contains("cmn")
            }
            .sortedByDescending { v ->
                var score = 0
                val n = v.name.lowercase(Locale.ROOT)
                if (v.quality >= Voice.QUALITY_HIGH) score += 40
                if (n.contains("network") || n.contains("neural") || n.contains("natural")) score += 30
                if (v.locale == Locale.SIMPLIFIED_CHINESE || v.locale.country.equals("CN", ignoreCase = true)) score += 20
                if (!v.isNetworkConnectionRequired) score += 10
                score
            }

        val options = zhVoices.mapIndexed { idx, v ->
            val region = v.locale.displayCountry.ifEmpty { v.locale.toLanguageTag() }
            val highQ = v.quality >= Voice.QUALITY_HIGH
            val label = buildString {
                if (idx == 0) append("⭐ ")
                append("Mandarin ($region)")
                if (highQ) append(" · Natural")
                append(" [${v.name.takeLast(12)}]")
            }
            MandarinVoiceOption(
                name = v.name,
                displayName = label,
                isHighQuality = highQ
            )
        }
        _availableVoices.value = options
        applyPreferredVoice()
    }

    fun updateSettings(speechRate: Float, voiceName: String) {
        currentSpeechRate = speechRate.coerceIn(0.55f, 1.35f)
        preferredVoiceName = voiceName
        applyPreferredVoice()
    }

    private fun applyPreferredVoice() {
        if (!isTtsReady) return
        val engine = tts ?: return
        try {
            val voices = engine.voices.orEmpty()
            val match = voices.firstOrNull { it.name == preferredVoiceName }
                ?: voices.firstOrNull {
                    it.locale == Locale.SIMPLIFIED_CHINESE && it.quality >= Voice.QUALITY_HIGH
                }
                ?: voices.firstOrNull {
                    it.locale?.language?.equals("zh", ignoreCase = true) == true
                }
            if (match != null) {
                engine.voice = match
            } else {
                engine.language = Locale.SIMPLIFIED_CHINESE
            }
        } catch (_: Exception) {
            engine.language = Locale.SIMPLIFIED_CHINESE
        }
    }

    fun speakMandarin(text: String, customRate: Float? = null) {
        if (!isTtsReady || text.isBlank()) return
        val engine = tts ?: return
        val naturalText = text.replace("。", "， ")
        engine.setSpeechRate(customRate ?: currentSpeechRate)
        engine.setPitch(1.03f)
        engine.speak(naturalText, TextToSpeech.QUEUE_FLUSH, null, "maz_tts_${System.nanoTime()}")
    }

    fun stopAllSfx() {
        stopRunnable?.let { mainHandler.removeCallbacks(it) }
        stopRunnable = null
        try {
            mediaPlayer?.let { mp ->
                if (mp.isPlaying) mp.stop()
                mp.release()
            }
        } catch (_: Exception) {
        }
        mediaPlayer = null
    }

    fun playCorrectFeedback(soundFxEnabled: Boolean, followUpMandarin: String? = null) {
        if (!soundFxEnabled) {
            if (!followUpMandarin.isNullOrBlank()) speakMandarin(followUpMandarin)
            return
        }
        stopAllSfx()
        try {
            val mp = MediaPlayer.create(context, R.raw.sfx_betul)
            if (mp == null) {
                if (!followUpMandarin.isNullOrBlank()) speakMandarin(followUpMandarin)
                return
            }
            mediaPlayer = mp
            val finishAndSpeak = {
                stopAllSfx()
                if (!followUpMandarin.isNullOrBlank()) {
                    speakMandarin(followUpMandarin)
                }
            }
            mp.setOnCompletionListener { finishAndSpeak() }
            mp.start()
            val stopper = Runnable { finishAndSpeak() }
            stopRunnable = stopper
            mainHandler.postDelayed(stopper, 2600L)
        } catch (_: Exception) {
            if (!followUpMandarin.isNullOrBlank()) speakMandarin(followUpMandarin)
        }
    }

    fun playWrongFeedback(soundFxEnabled: Boolean) {
        if (!soundFxEnabled) return
        stopAllSfx()
        try {
            val mp = MediaPlayer.create(context, R.raw.sfx_salah) ?: return
            mediaPlayer = mp
            mp.setOnCompletionListener { stopAllSfx() }
            mp.start()
            val stopper = Runnable { stopAllSfx() }
            stopRunnable = stopper
            mainHandler.postDelayed(stopper, 2600L)
        } catch (_: Exception) {
        }
    }

    fun release() {
        stopAllSfx()
        try {
            tts?.stop()
            tts?.shutdown()
        } catch (_: Exception) {
        }
        tts = null
    }
}

package com.example.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.model.LevelId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.mazDataStore: DataStore<Preferences> by preferencesDataStore(name = "maz_mandarin_prefs")

data class UserPreferences(
    val currentLevel: LevelId = LevelId.T1,
    val showPinyin: Boolean = true,
    val soundFx: Boolean = true,
    val autoSpeak: Boolean = false,
    val darkTheme: Boolean = false,
    val speechRate: Float = 0.88f,
    val preferredVoiceName: String = "",
    val lastLevelKey: String = "",
    val lastChapterId: String = "",
    val lastStationId: String = "",
    val lastItemIndex: Int = 0,
    val bestSpeedDrillScore: Int = 0,
    val studentName: String = "Pelajar BCK",
    val schoolCode: String = ""
)

class PreferencesRepository(private val context: Context) {

    private object Keys {
        val CURRENT_LEVEL = stringPreferencesKey("current_level")
        val SHOW_PINYIN = booleanPreferencesKey("show_pinyin")
        val SOUND_FX = booleanPreferencesKey("sound_fx")
        val AUTO_SPEAK = booleanPreferencesKey("auto_speak")
        val DARK_THEME = booleanPreferencesKey("dark_theme")
        val SPEECH_RATE = floatPreferencesKey("speech_rate")
        val PREFERRED_VOICE = stringPreferencesKey("preferred_voice")
        val LAST_LEVEL = stringPreferencesKey("last_level")
        val LAST_CHAPTER = stringPreferencesKey("last_chapter")
        val LAST_STATION = stringPreferencesKey("last_station")
        val LAST_ITEM_INDEX = intPreferencesKey("last_item_index")
        val BEST_SPEED_DRILL = intPreferencesKey("best_speed_drill")
        val STUDENT_NAME = stringPreferencesKey("student_name")
        val SCHOOL_CODE = stringPreferencesKey("school_code")
    }

    val preferencesFlow: Flow<UserPreferences> = context.mazDataStore.data.map { prefs ->
        UserPreferences(
            currentLevel = LevelId.fromKey(prefs[Keys.CURRENT_LEVEL] ?: LevelId.T1.key),
            showPinyin = prefs[Keys.SHOW_PINYIN] ?: true,
            soundFx = prefs[Keys.SOUND_FX] ?: true,
            autoSpeak = prefs[Keys.AUTO_SPEAK] ?: false,
            darkTheme = prefs[Keys.DARK_THEME] ?: false,
            speechRate = prefs[Keys.SPEECH_RATE] ?: 0.88f,
            preferredVoiceName = prefs[Keys.PREFERRED_VOICE] ?: "",
            lastLevelKey = prefs[Keys.LAST_LEVEL] ?: "",
            lastChapterId = prefs[Keys.LAST_CHAPTER] ?: "",
            lastStationId = prefs[Keys.LAST_STATION] ?: "",
            lastItemIndex = prefs[Keys.LAST_ITEM_INDEX] ?: 0,
            bestSpeedDrillScore = prefs[Keys.BEST_SPEED_DRILL] ?: 0,
            studentName = prefs[Keys.STUDENT_NAME] ?: "Pelajar BCK",
            schoolCode = prefs[Keys.SCHOOL_CODE] ?: ""
        )
    }

    suspend fun setCurrentLevel(level: LevelId) {
        context.mazDataStore.edit { it[Keys.CURRENT_LEVEL] = level.key }
    }

    suspend fun setShowPinyin(enabled: Boolean) {
        context.mazDataStore.edit { it[Keys.SHOW_PINYIN] = enabled }
    }

    suspend fun setSoundFx(enabled: Boolean) {
        context.mazDataStore.edit { it[Keys.SOUND_FX] = enabled }
    }

    suspend fun setAutoSpeak(enabled: Boolean) {
        context.mazDataStore.edit { it[Keys.AUTO_SPEAK] = enabled }
    }

    suspend fun setDarkTheme(enabled: Boolean) {
        context.mazDataStore.edit { it[Keys.DARK_THEME] = enabled }
    }

    suspend fun setSpeechRate(rate: Float) {
        context.mazDataStore.edit { it[Keys.SPEECH_RATE] = rate }
    }

    suspend fun setPreferredVoice(voiceName: String) {
        context.mazDataStore.edit { it[Keys.PREFERRED_VOICE] = voiceName }
    }

    suspend fun recordLastActivity(level: LevelId, chapterId: String, stationId: String, itemIndex: Int) {
        context.mazDataStore.edit {
            it[Keys.LAST_LEVEL] = level.key
            it[Keys.LAST_CHAPTER] = chapterId
            it[Keys.LAST_STATION] = stationId
            it[Keys.LAST_ITEM_INDEX] = itemIndex
        }
    }

    suspend fun clearLastActivityIfMatchesLevel(level: LevelId) {
        context.mazDataStore.edit {
            if (it[Keys.LAST_LEVEL] == level.key) {
                it[Keys.LAST_LEVEL] = ""
                it[Keys.LAST_CHAPTER] = ""
                it[Keys.LAST_STATION] = ""
                it[Keys.LAST_ITEM_INDEX] = 0
            }
        }
    }

    suspend fun updateBestSpeedDrillScore(score: Int) {
        context.mazDataStore.edit { prefs ->
            val current = prefs[Keys.BEST_SPEED_DRILL] ?: 0
            if (score > current) {
                prefs[Keys.BEST_SPEED_DRILL] = score
            }
        }
    }

    suspend fun setStudentProfile(name: String, schoolCode: String) {
        context.mazDataStore.edit {
            it[Keys.STUDENT_NAME] = name.trim().ifEmpty { "Pelajar BCK" }
            it[Keys.SCHOOL_CODE] = schoolCode.trim().uppercase()
        }
    }
}

package com.example.data.local

import android.content.Context
import com.example.data.model.ChapterMeta
import com.example.data.model.FlashcardItem
import com.example.data.model.LevelId
import com.example.data.model.QuizItem
import com.example.data.model.QuizType
import com.example.data.model.RankInfo
import com.example.data.model.SpeedDrillQuestion
import com.example.data.model.StationMeta
import com.example.data.model.ToneExample
import org.json.JSONArray
import org.json.JSONObject

class SyllabusRepository(private val context: Context) {

    private var cachedJson: JSONObject? = null
    private var cachedAllFlashcards: List<FlashcardItem>? = null

    val stationsMeta: List<StationMeta> = listOf(
        StationMeta(
            id = "s1",
            stepLabel = "Stesen 1 · Pengenalan",
            icon = "🃏",
            title = "Kad Imbas Pintar (Flashcard)",
            desc = "Kenali aksara Hanzi, gambar, bunyi Pinyin dan maksud BM.",
            isFlashcard = true
        ),
        StationMeta(
            id = "s2",
            stepLabel = "Stesen 2 · Pengecaman Bunyi",
            icon = "🎧",
            title = "Pinyin & 4 Nada (Ting Li)",
            desc = "Dengar sebutan dan kenal pasti Pinyin serta nada yang tepat."
        ),
        StationMeta(
            id = "s3",
            stepLabel = "Stesen 3 · Struktur Tulisan",
            icon = "🧩",
            title = "Radikal & Goresan (Hanzi)",
            desc = "Fahami komponen asas radikal dan urutan goresan aksara."
        ),
        StationMeta(
            id = "s4",
            stepLabel = "Stesen 4 · Pengukuhan Makna",
            icon = "📖",
            title = "Padanan Kosa Kata (Shengci)",
            desc = "Uji kefahaman maksud perkataan teras dalam Bahasa Melayu."
        ),
        StationMeta(
            id = "s5",
            stepLabel = "Stesen 5 · Aplikasi Lisan",
            icon = "💬",
            title = "Melengkapkan Dialog (Duihua)",
            desc = "Pilih respons perbualan harian yang paling sopan dan tepat."
        ),
        StationMeta(
            id = "s6",
            stepLabel = "Stesen 6 · Sintaksis & Tatabahasa",
            icon = "🧱",
            title = "Susun Ayat Gramatis",
            desc = "Susun blok perkataan mengikut susunan ayat Mandarin."
        ),
        StationMeta(
            id = "s7",
            stepLabel = "Stesen 7 · Penilaian Akhir",
            icon = "🧠",
            title = "Pemahaman Petikan (Yuedu)",
            desc = "Baca petikan pendek bersepadu dan jawab soalan pemahaman."
        )
    )

    val chaptersByLevel: Map<LevelId, List<ChapterMeta>> = mapOf(
        LevelId.T1 to listOf(
            ChapterMeta("c1", "Bab 1", "👋", "你好 (Apa Khabar)", "第一课 · 你好", "Ucapan sapaan, memperkenalkan diri, ucapan harian & goresan asas."),
            ChapterMeta("c2", "Bab 2", "🎂", "我的生日 (Hari Lahir Saya)", "第二课 · 我的生日", "Nombor 0–999, tarikh, hari dalam seminggu & menyatakan masa."),
            ChapterMeta("c3", "Bab 3", "🏃", "我的身体 (Badan Saya)", "第三课 · 我的身体", "Anggota badan, jari-jemari, pancaindera & teka-teki."),
            ChapterMeta("c4", "Bab 4", "👨‍👩‍👧‍👦", "我的家人 (Keluarga Saya)", "第四课 · 我的家人", "Panggilan ahli keluarga besar, umur & salasilah keluarga."),
            ChapterMeta("c5", "Bab 5", "🏫", "课室里 (Di Dalam Kelas)", "第五课 · 课室里", "Peralatan bilik darjah, alat tulis, penjodoh bilangan & arahan."),
            ChapterMeta("c6", "Bab 6", "🏢", "我的学校 (Sekolah Saya)", "第六课 · 我的学校", "Kemudahan di sekolah, kata arah (kiri/kanan/depan/belakang)."),
            ChapterMeta("c7", "Bab 7", "🍜", "去食堂 (Ke Kantin)", "第七课 · 去食堂", "Memesan makanan & minuman, harga RM & penjodoh bilangan.")
        ),
        LevelId.T2 to listOf(
            ChapterMeta("c1", "Bab 1", "🏸", "李端的一天与爱好", "单元一 · Rutin & Hobi", "Jadual harian Li Duan, aktiviti sukan, hobi dan masa lapang."),
            ChapterMeta("c2", "Bab 2", "✈️", "假期与交通工具", "单元二 · Cuti & Pengangkutan", "Diari percutian Meina, cuaca dan pengangkutan darat, udara & laut."),
            ChapterMeta("c3", "Bab 3", "🥭", "本地与外国水果", "单元三 · Buah-buahan", "Buah-buahan tempatan Malaysia, buah import, harga & perbandingan."),
            ChapterMeta("c4", "Bab 4", "🤒", "健康与看医生", "单元四 · Kesihatan & Doktor", "Simptom penyakit, berjumpa doktor, e-mel cuti sakit & penjagaan diri."),
            ChapterMeta("c5", "Bab 5", "🥬", "菜市与美食餐馆", "单元五 · Pasar & Restoran", "Sayur-sayuran, lauk di pasar basah dan memesan hidangan pelbagai kaum."),
            ChapterMeta("c6", "Bab 6", "🦏", "动物园与人物外貌", "单元六 · Zoo & Rupa Paras", "Haiwan dilindungi di Zoo Negara (Tapir Malaysia) & pola ayat 又……又……"),
            ChapterMeta("c7", "Bab 7", "🇲🇾", "志愿与多元马来西亚", "单元七至八 · Cita-cita & Negara", "Kerjaya impian Hisham serta kepelbagaian kaum, perayaan & makanan Malaysia.")
        ),
        LevelId.T3 to listOf(
            ChapterMeta("c1", "Bab 1", "🏠", "姑姑的新房子", "单元一 · Rumah Baharu", "Bahagian rumah, perabot, adab melayan tetamu & pola ayat 一边……一边……"),
            ChapterMeta("c2", "Bab 2", "🐱", "学习关怀", "单元二 · Prihatin & Kasih", "Menyayangi haiwan, sokongan menghadapi peperiksaan & pola 只要……就……"),
            ChapterMeta("c3", "Bab 3", "🏆", "学校运动会", "单元三 · Sukan Sekolah", "Acara balapan & padang, semangat kesukanan & notis rasmi sekolah."),
            ChapterMeta("c4", "Bab 4", "🛍️", "去购物与逛书店", "单元四至五 · Beli-belah & Buku", "Bahagian di pusat beli-belah, kedai buku, penjodoh bilangan & alat tulis."),
            ChapterMeta("c5", "Bab 5", "🏛️", "爱护公物，人人有责", "单元六 · Kemudahan Awam", "Etika di tempat awam, menjaga kebersihan & perubahan nada ke-3."),
            ChapterMeta("c6", "Bab 6", "🥮", "华人的传统节日", "单元七 · Perayaan Tradisional", "Pesta Kuih Bulan, Dongzhi, Chap Goh Mei & adat tradisi masyarakat Cina."),
            ChapterMeta("c7", "Bab 7", "🏔️", "我爱旅游", "单元八 · Pelancongan Malaysia", "Gunung Kinabalu, Bunga Rafflesia, mercu tanda KL & destinasi Malaysia.")
        )
    )

    val toneExamples: List<ToneExample> = listOf(
        ToneExample(1, "ˉ", "Nada 1 · Tinggi & Datar", "Bunyi tinggi sekata (55)", "妈", "mā", "Ibu", "妈"),
        ToneExample(2, "ˊ", "Nada 2 · Meningkat Naik", "Bunyi naik seperti bertanya (35)", "麻", "má", "Kebas / Rami", "麻"),
        ToneExample(3, "ˇ", "Nada 3 · Turun lalu Naik", "Bunyi rendah melengkung (214)", "马", "mǎ", "Kuda", "马"),
        ToneExample(4, "ˋ", "Nada 4 · Turun Tajam", "Bunyi tegas & pendek (51)", "骂", "mà", "Marah / Memarahi", "骂")
    )

    val ranks: List<RankInfo> = listOf(
        RankInfo("Pelajar Muda", "新星学员", 0, 150, "🌱"),
        RankInfo("Peneroka Pinyin", "拼音探索者", 150, 450, "🎧"),
        RankInfo("Sarjana Hanzi", "汉字达人", 450, 1000, "✍️"),
        RankInfo("Juara Dialog", "对话王者", 1000, 2000, "🏆"),
        RankInfo("Mahaguru BCK", "华语宗师", 2000, 5000, "👑")
    )

    fun getRankForXp(xp: Int): RankInfo {
        return ranks.lastOrNull { xp >= it.minXp } ?: ranks.first()
    }

    fun getChapters(levelId: LevelId): List<ChapterMeta> = chaptersByLevel[levelId].orEmpty()

    fun getChapter(levelId: LevelId, chapterId: String): ChapterMeta {
        return getChapters(levelId).firstOrNull { it.id == chapterId } ?: getChapters(levelId).first()
    }

    fun getStationMeta(stationId: String): StationMeta {
        return stationsMeta.firstOrNull { it.id == stationId } ?: stationsMeta.first()
    }

    private fun loadJson(): JSONObject {
        cachedJson?.let { return it }
        val text = context.assets.open("syllabus_data.json").bufferedReader().use { it.readText() }
        return JSONObject(text).also { cachedJson = it }
    }

    fun getFlashcards(levelId: LevelId, chapterId: String): List<FlashcardItem> {
        val root = loadJson()
        val arr = root.optJSONObject(levelId.key)
            ?.optJSONObject(chapterId)
            ?.optJSONArray("s1") ?: return emptyList()
        val chapNum = getChapter(levelId, chapterId).numLabel
        return buildList {
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                add(
                    FlashcardItem(
                        emoji = obj.optString("emoji", "🀄"),
                        hanzi = obj.optString("hanzi"),
                        pinyin = obj.optString("pinyin"),
                        meaning = obj.optString("meaning"),
                        exCn = obj.optString("exCn"),
                        exPy = obj.optString("exPy"),
                        exBm = obj.optString("exBm"),
                        levelId = levelId,
                        chapterNum = chapNum
                    )
                )
            }
        }
    }

    fun getQuizItems(levelId: LevelId, chapterId: String, stationId: String): List<QuizItem> {
        val root = loadJson()
        val arr = root.optJSONObject(levelId.key)
            ?.optJSONObject(chapterId)
            ?.optJSONArray(stationId) ?: return emptyList()
        val chap = getChapter(levelId, chapterId)
        return buildList {
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                val typeStr = obj.optString("type", "mcq")
                val type = when (typeStr) {
                    "order" -> QuizType.ORDER
                    "comprehension" -> QuizType.COMPREHENSION
                    else -> QuizType.MCQ
                }
                add(
                    QuizItem(
                        type = type,
                        emoji = obj.optString("emoji", ""),
                        instruction = obj.optString("instruction"),
                        hanzi = obj.optString("hanzi", ""),
                        pinyin = obj.optString("pinyin", ""),
                        speech = obj.optString("speech", ""),
                        options = obj.optJSONArray("options").toStringList(),
                        answer = obj.optString("answer", ""),
                        promptText = obj.optString("promptText", ""),
                        words = obj.optJSONArray("words").toStringList(),
                        answerOrder = obj.optJSONArray("answerOrder").toStringList(),
                        passage = obj.optString("passage", ""),
                        question = obj.optString("question", ""),
                        explanation = obj.optString("explanation", ""),
                        sourceTag = "${levelId.shortLabel} · ${chap.numLabel}"
                    )
                )
            }
        }
    }

    fun getAllFlashcards(): List<FlashcardItem> {
        cachedAllFlashcards?.let { return it }
        val all = buildList {
            LevelId.entries.forEach { level ->
                getChapters(level).forEach { chap ->
                    addAll(getFlashcards(level, chap.id))
                }
            }
        }
        cachedAllFlashcards = all
        return all
    }

    fun generateSpeedDrill(levelId: LevelId, count: Int = 10): List<SpeedDrillQuestion> {
        val pool = mutableListOf<SpeedDrillQuestion>()
        val mcqStations = listOf("s2", "s3", "s4", "s5")
        getChapters(levelId).forEach { chap ->
            mcqStations.forEach { stId ->
                getQuizItems(levelId, chap.id, stId)
                    .filter { it.type == QuizType.MCQ && it.options.isNotEmpty() }
                    .forEach { item ->
                        pool.add(SpeedDrillQuestion(item, "${chap.numLabel}: ${chap.title}"))
                    }
            }
        }
        return pool.shuffled().take(count)
    }

    private fun JSONArray?.toStringList(): List<String> {
        if (this == null) return emptyList()
        return List(length()) { idx -> optString(idx) }
    }
}

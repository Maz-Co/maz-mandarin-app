package com.example.domain

import com.example.data.local.DailyActivityEntity
import com.example.data.local.StationProgressEntity
import com.example.data.local.UnlockedBadgeEntity
import com.example.data.model.AchievementBadge
import com.example.data.model.BadgeCategory
import com.example.data.model.BadgeTier

data class AchievementSnapshot(
    val progressList: List<StationProgressEntity>,
    val dailyActivities: List<DailyActivityEntity>,
    val streakDays: Int,
    val todayXp: Int,
    val totalXp: Int,
    val bestSpeedDrillScore: Int,
    val persistedUnlocks: Map<String, UnlockedBadgeEntity>
)

/**
 * Pure, deterministic milestone evaluation engine for MAZ Mandarin achievement badges.
 * Computes live progress toward every badge, determines newly achieved milestones,
 * and identifies the closest upcoming badge to motivate students.
 */
object AchievementEngine {

    private data class BadgeSpec(
        val id: String,
        val emoji: String,
        val hanziSeal: String,
        val title: String,
        val shortDesc: String,
        val milestoneTip: String,
        val category: BadgeCategory,
        val tier: BadgeTier,
        val targetValue: Int,
        val unitLabel: String,
        val xpReward: Int,
        val measure: (AchievementSnapshot) -> Int
    )

    private val specs: List<BadgeSpec> = listOf(
        // --- MILESTONE (Silibus) ---
        BadgeSpec(
            id = "first_step",
            emoji = "🚀",
            hanziSeal = "初",
            title = "Langkah Pertama",
            shortDesc = "Tamatkan stesen pertama anda",
            milestoneTip = "Selesaikan mana-mana 1 stesen (Kad Imbas atau Kuiz) dalam mana-mana bab.",
            category = BadgeCategory.MILESTONE,
            tier = BadgeTier.BRONZE,
            targetValue = 1,
            unitLabel = "stesen",
            xpReward = 25,
            measure = { it.progressList.size }
        ),
        BadgeSpec(
            id = "chapter_champ",
            emoji = "🏆",
            hanziSeal = "冠",
            title = "Juara Bab",
            shortDesc = "Lengkapkan semua 7 stesen satu bab",
            milestoneTip = "Tamatkan Stesen 1 hingga Stesen 7 bagi mana-mana satu bab pilihan anda.",
            category = BadgeCategory.MILESTONE,
            tier = BadgeTier.GOLD,
            targetValue = 7,
            unitLabel = "stesen bab",
            xpReward = 75,
            measure = { snap ->
                snap.progressList
                    .groupBy { "${it.levelId}_${it.chapterId}" }
                    .maxOfOrNull { it.value.size } ?: 0
            }
        ),
        BadgeSpec(
            id = "bck_explorer",
            emoji = "🇲🇾",
            hanziSeal = "游",
            title = "Penjelajah BCK",
            shortDesc = "Cuba latihan merentas T1, T2 & T3",
            milestoneTip = "Selesaikan sekurang-kurangnya 1 stesen pada setiap tingkatan (Tingkatan 1, 2 dan 3).",
            category = BadgeCategory.MILESTONE,
            tier = BadgeTier.SILVER,
            targetValue = 3,
            unitLabel = "tingkatan",
            xpReward = 50,
            measure = { snap -> snap.progressList.map { it.levelId }.toSet().size }
        ),
        BadgeSpec(
            id = "form_scholar",
            emoji = "🎓",
            hanziSeal = "宗",
            title = "Sarjana BCK",
            shortDesc = "Selesaikan 25 stesen pembelajaran",
            milestoneTip = "Teruskan penguasaan silibus sehingga mencapai 25 stesen selesai secara keseluruhan.",
            category = BadgeCategory.MILESTONE,
            tier = BadgeTier.JADE,
            targetValue = 25,
            unitLabel = "stesen",
            xpReward = 150,
            measure = { it.progressList.size }
        ),

        // --- MASTERY (Penguasaan Bahasa) ---
        BadgeSpec(
            id = "perfect_score",
            emoji = "🎯",
            hanziSeal = "满",
            title = "Sempurna 5/5",
            shortDesc = "Capai skor penuh dalam kuiz",
            milestoneTip = "Jawab kesemua 5 soalan dengan betul tanpa salah dalam mana-mana stesen kuiz (Stesen 2–7).",
            category = BadgeCategory.MASTERY,
            tier = BadgeTier.BRONZE,
            targetValue = 1,
            unitLabel = "kuiz 5/5",
            xpReward = 35,
            measure = { snap ->
                snap.progressList.count { it.stationId != "s1" && it.bestScore >= 5 }
            }
        ),
        BadgeSpec(
            id = "flash_master",
            emoji = "🃏",
            hanziSeal = "词",
            title = "Pakar Kad Imbas",
            shortDesc = "Kuasai 3 dek Kad Imbas 3D",
            milestoneTip = "Tamatkan Stesen 1 (Kad Imbas Pintar) bagi 3 bab berbeza untuk memperkaya kosa kata.",
            category = BadgeCategory.MASTERY,
            tier = BadgeTier.SILVER,
            targetValue = 3,
            unitLabel = "dek",
            xpReward = 45,
            measure = { snap -> snap.progressList.count { it.stationId == "s1" } }
        ),
        BadgeSpec(
            id = "syntax_architect",
            emoji = "🧱",
            hanziSeal = "句",
            title = "Arkitek Ayat",
            shortDesc = "Tamatkan 3 stesen Susun Ayat",
            milestoneTip = "Kuasai tatabahasa Mandarin dengan melengkapkan Stesen 6 (Susun Ayat) bagi 3 bab.",
            category = BadgeCategory.MASTERY,
            tier = BadgeTier.SILVER,
            targetValue = 3,
            unitLabel = "stesen s6",
            xpReward = 55,
            measure = { snap -> snap.progressList.count { it.stationId == "s6" } }
        ),
        BadgeSpec(
            id = "reading_master",
            emoji = "🧠",
            hanziSeal = "读",
            title = "Bintang Yuedu",
            shortDesc = "Tamatkan 3 stesen Pemahaman",
            milestoneTip = "Lengkapkan Stesen 7 (Pemahaman Petikan Yuedu) pada 3 bab berbeza.",
            category = BadgeCategory.MASTERY,
            tier = BadgeTier.GOLD,
            targetValue = 3,
            unitLabel = "stesen s7",
            xpReward = 70,
            measure = { snap -> snap.progressList.count { it.stationId == "s7" } }
        ),

        // --- CONSISTENCY (Konsistensi & XP) ---
        BadgeSpec(
            id = "daily_goal_hero",
            emoji = "☀️",
            hanziSeal = "勤",
            title = "Wira Misi Harian",
            shortDesc = "Capai sasaran 100 XP sehari",
            milestoneTip = "Kumpul sekurang-kurangnya 100 XP dalam satu hari melalui kad imbas, kuiz, atau cabaran kilat.",
            category = BadgeCategory.CONSISTENCY,
            tier = BadgeTier.BRONZE,
            targetValue = 100,
            unitLabel = "XP hari ini",
            xpReward = 30,
            measure = { snap ->
                val maxDay = snap.dailyActivities.maxOfOrNull { it.xpEarned } ?: 0
                maxOf(snap.todayXp, maxDay)
            }
        ),
        BadgeSpec(
            id = "streak_flame",
            emoji = "🔥",
            hanziSeal = "炎",
            title = "Api Semangat 3 Hari",
            shortDesc = "Belajar 3 hari berturut-turut",
            milestoneTip = "Buka aplikasi dan kumpul XP setiap hari selama 3 hari berturut-turut.",
            category = BadgeCategory.CONSISTENCY,
            tier = BadgeTier.GOLD,
            targetValue = 3,
            unitLabel = "hari",
            xpReward = 80,
            measure = { it.streakDays }
        ),
        BadgeSpec(
            id = "star_collector",
            emoji = "⭐",
            hanziSeal = "星",
            title = "Pengumpul 30 Bintang",
            shortDesc = "Kumpul 30 bintang merentas bab",
            milestoneTip = "Raih 3 bintang pada stesen pembelajaran anda sehingga mengumpul 30 bintang keseluruhan.",
            category = BadgeCategory.CONSISTENCY,
            tier = BadgeTier.GOLD,
            targetValue = 30,
            unitLabel = "bintang",
            xpReward = 90,
            measure = { snap -> snap.progressList.sumOf { it.stars } }
        ),
        BadgeSpec(
            id = "xp_titan",
            emoji = "💎",
            hanziSeal = "辉",
            title = "Permata 500 XP",
            shortDesc = "Kumpul 500 XP keseluruhan",
            milestoneTip = "Terus berlatih untuk mengumpul jumlah 500 XP sepanjang perjalanan Bahasa Cina anda.",
            category = BadgeCategory.CONSISTENCY,
            tier = BadgeTier.JADE,
            targetValue = 500,
            unitLabel = "XP",
            xpReward = 120,
            measure = { it.totalXp }
        ),

        // --- CHALLENGE (Cabaran Kilat) ---
        BadgeSpec(
            id = "speed_runner",
            emoji = "⚡",
            hanziSeal = "速",
            title = "Kilat Mandarin",
            shortDesc = "Raih 600+ mata Cabaran Kilat",
            milestoneTip = "Sertai Cabaran Kilat 10 Soalan di tab Cabaran dan capai sekurang-kurangnya 600 mata.",
            category = BadgeCategory.CHALLENGE,
            tier = BadgeTier.SILVER,
            targetValue = 600,
            unitLabel = "mata",
            xpReward = 50,
            measure = { it.bestSpeedDrillScore }
        ),
        BadgeSpec(
            id = "speed_legend",
            emoji = "👑",
            hanziSeal = "王",
            title = "Legenda Kilat",
            shortDesc = "Raih 1000+ mata Cabaran Kilat",
            milestoneTip = "Jawab pantas dengan kombo berturut-turut dalam Cabaran Kilat untuk melepasi 1000 mata!",
            category = BadgeCategory.CHALLENGE,
            tier = BadgeTier.JADE,
            targetValue = 1000,
            unitLabel = "mata",
            xpReward = 120,
            measure = { it.bestSpeedDrillScore }
        )
    )

    fun evaluate(snapshot: AchievementSnapshot): List<AchievementBadge> {
        return specs.map { spec ->
            val rawValue = spec.measure(snapshot).coerceAtLeast(0)
            val persisted = snapshot.persistedUnlocks[spec.id]
            val meetsNow = rawValue >= spec.targetValue
            val unlocked = persisted != null || meetsNow
            val clampedValue = if (unlocked) spec.targetValue else rawValue.coerceAtMost(spec.targetValue)
            AchievementBadge(
                id = spec.id,
                emoji = spec.emoji,
                hanziSeal = spec.hanziSeal,
                title = spec.title,
                desc = spec.shortDesc,
                milestoneTip = spec.milestoneTip,
                category = spec.category,
                tier = spec.tier,
                currentValue = clampedValue,
                targetValue = spec.targetValue,
                unitLabel = spec.unitLabel,
                xpReward = spec.xpReward,
                isUnlocked = unlocked,
                unlockedAt = persisted?.unlockedAt
            )
        }
    }

    /**
     * Picks the closest locked achievement (highest completion ratio > 0, or lowest-tier starter)
     * so students get an actionable next-milestone nudge.
     */
    fun nextClosestBadge(badges: List<AchievementBadge>): AchievementBadge? {
        val locked = badges.filter { !it.isUnlocked }
        if (locked.isEmpty()) return null
        return locked.maxWithOrNull(
            compareBy<AchievementBadge> { it.progressFraction }
                .thenByDescending { -it.tier.ordinal }
        )
    }
}

package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unlocked_badges")
data class UnlockedBadgeEntity(
    @PrimaryKey val badgeId: String,
    val unlockedAt: Long = System.currentTimeMillis()
)

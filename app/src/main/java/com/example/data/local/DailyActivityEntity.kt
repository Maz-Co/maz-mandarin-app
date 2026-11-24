package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_activity")
data class DailyActivityEntity(
    @PrimaryKey val dateKey: String, // Format: YYYY-MM-DD
    val xpEarned: Int = 0,
    val stationsCompleted: Int = 0,
    val correctAnswers: Int = 0,
    val totalAnswers: Int = 0
)

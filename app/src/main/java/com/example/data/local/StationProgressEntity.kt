package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "station_progress")
data class StationProgressEntity(
    @PrimaryKey val stationKey: String, // e.g., "t1_c1_s1"
    val levelId: String,
    val chapterId: String,
    val stationId: String,
    val stars: Int = 3,
    val bestScore: Int = 5,
    val totalQuestions: Int = 5,
    val completedAt: Long = System.currentTimeMillis()
)

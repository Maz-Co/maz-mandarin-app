package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MazDao {

    @Query("SELECT * FROM station_progress")
    fun observeAllProgress(): Flow<List<StationProgressEntity>>

    @Query("SELECT * FROM station_progress WHERE stationKey = :stationKey LIMIT 1")
    suspend fun getProgressByKey(stationKey: String): StationProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProgress(entity: StationProgressEntity)

    @Query("DELETE FROM station_progress WHERE levelId = :levelId")
    suspend fun clearProgressForLevel(levelId: String)

    @Query("SELECT * FROM daily_activity ORDER BY dateKey DESC")
    fun observeAllDailyActivity(): Flow<List<DailyActivityEntity>>

    @Query("SELECT * FROM daily_activity WHERE dateKey = :dateKey LIMIT 1")
    suspend fun getDailyActivity(dateKey: String): DailyActivityEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertDailyActivity(entity: DailyActivityEntity)

    @Query("SELECT * FROM unlocked_badges ORDER BY unlockedAt DESC")
    fun observeUnlockedBadges(): Flow<List<UnlockedBadgeEntity>>

    @Query("SELECT badgeId FROM unlocked_badges")
    suspend fun getUnlockedBadgeIds(): List<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUnlockedBadge(entity: UnlockedBadgeEntity): Long
}

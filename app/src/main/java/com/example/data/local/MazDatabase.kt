package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
        StationProgressEntity::class,
        DailyActivityEntity::class,
        UnlockedBadgeEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class MazDatabase : RoomDatabase() {
    abstract fun mazDao(): MazDao

    companion object {
        @Volatile
        private var INSTANCE: MazDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `unlocked_badges` (
                        `badgeId` TEXT NOT NULL,
                        `unlockedAt` INTEGER NOT NULL,
                        PRIMARY KEY(`badgeId`)
                    )
                    """.trimIndent()
                )
            }
        }

        fun getInstance(context: Context): MazDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MazDatabase::class.java,
                    "maz_mandarin_akademik.db"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}

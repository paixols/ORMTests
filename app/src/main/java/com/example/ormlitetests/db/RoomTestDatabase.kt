package com.example.ormlitetests.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ormlitetests.BuildConfig
import com.example.ormlitetests.db.tables.TestEntity
import com.example.ormlitetests.db.dao.TestEntityDao

@Database(
    entities = [
        TestEntity::class
    ],
    version = BuildConfig.VERSION_CODE
)
abstract class RoomTestDatabase : RoomDatabase() {

    abstract fun testEntityDao(): TestEntityDao

    companion object {

        @Volatile
        private var instance: RoomTestDatabase? = null

        fun getInstance(context: Context): RoomTestDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): RoomTestDatabase {
            return Room
                .databaseBuilder(context, RoomTestDatabase::class.java, BuildConfig.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

    }

}
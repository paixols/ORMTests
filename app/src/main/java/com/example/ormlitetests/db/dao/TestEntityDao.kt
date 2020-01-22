package com.example.ormlitetests.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.ormlitetests.db.tables.TestEntity

@Dao
interface TestEntityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createTestEntry(testEntity: TestEntity)

}
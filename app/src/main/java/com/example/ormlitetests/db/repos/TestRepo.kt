package com.example.ormlitetests.db.repos

import android.util.Log
import com.example.ormlitetests.db.dao.TestEntityDao
import com.example.ormlitetests.db.tables.TestEntity

class TestRepo private constructor(
    private val testDao: TestEntityDao
) {

    fun createTestEntry(testEntity: TestEntity) {
        testDao.createTestEntry(testEntity = testEntity)
        Log.d("Repo will create table:", "true")
    }

    companion object {

        @Volatile
        private var instance: TestRepo? = null

        fun getInstance(testDao: TestEntityDao) = instance ?: TestRepo(testDao).also {
            instance = it
        }
    }
}
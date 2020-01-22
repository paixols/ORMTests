package com.example.ormlitetests.vm

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.ormlitetests.db.repos.TestRepo
import com.example.ormlitetests.db.tables.TestEntity
import com.example.ormlitetests.util.runOnIoThread

class TestEntityViewModel internal constructor(
    private val testRepo: TestRepo
) : ViewModel() {

    fun createTestEntry(testEntity: TestEntity) {
        runOnIoThread {
            testRepo.createTestEntry(
                testEntity = testEntity
            )
        }
    }
}
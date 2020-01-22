package com.example.ormlitetests.vm

import android.content.Context
import com.example.ormlitetests.db.RoomTestDatabase
import com.example.ormlitetests.db.repos.TestRepo

object ViewModelInjector {


    private fun getTestRepo(context: Context): TestRepo {
        return TestRepo.getInstance(
            RoomTestDatabase.getInstance(
                context
            ).testEntityDao()
        )
    }

    fun provideTestEntityViewModel(context: Context): TestEntityViewModelFactory {
        val repo = getTestRepo(context)
        return TestEntityViewModelFactory(repo)
    }

}
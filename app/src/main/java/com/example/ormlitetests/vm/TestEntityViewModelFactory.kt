package com.example.ormlitetests.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ormlitetests.db.repos.TestRepo

class TestEntityViewModelFactory(
    private val testRepo: TestRepo
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = TestEntityViewModel(testRepo) as T
}
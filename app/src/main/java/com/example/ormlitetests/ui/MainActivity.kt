package com.example.ormlitetests.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.ormlitetests.R
import com.example.ormlitetests.app.DbTestApp
import com.example.ormlitetests.db.managers.TestTableManager
import com.example.ormlitetests.db.tables.TestEntity
import com.example.ormlitetests.db.tables.TestTable
import com.example.ormlitetests.vm.TestEntityViewModel
import com.example.ormlitetests.vm.ViewModelInjector
import com.j256.ormlite.dao.Dao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    /**
     * Properties
     * */
    private var entryId: Long = 0
    private lateinit var mTestEntryViewModel: TestEntityViewModel

    /**
     * LifeCycle
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
        setupViewModels()
    }

    /*
    * UI
    * */
    private fun setupUi() {
        btn_create_orm.setOnClickListener(this)
        btn_create_room.setOnClickListener(this)
    }

    /**
     * View Models
     * */

    private fun setupViewModels() {
        val factory = ViewModelInjector.provideTestEntityViewModel(this)
        mTestEntryViewModel = ViewModelProvider(this, factory)
            .get(TestEntityViewModel::class.java)
    }

    /**
     * DB transactions
     * */
    private fun createEntryInTestTable(testTable: TestTable) {
        TestTableManager.instance.createEntryInTable(
            dao = DbTestApp
                .instance
                .getDbConnector()
                .getDatabaseDao(TestTable::class.java) as Dao<TestTable, Int>,
            testTable = testTable
        )
    }

    private fun createTestEntryForOrmLite(
        id: Long,
        firstName: String,
        lastName: String,
        age: Int
    ): TestTable {
        return TestTable(id, firstName, lastName, age)
    }

    private fun createTestEntryForRoom(
        id: Long,
        firstName: String,
        lastName: String,
        age: Int
    ): TestEntity {
        return TestEntity(
            generated_id = id,
            firstName = firstName,
            lastName = lastName,
            age = age
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            /*
            * Create DB Entry using ORMLite
            * */
            btn_create_orm.id -> {
                createEntryInTestTable(
                    createTestEntryForOrmLite(
                        id = entryId++,
                        firstName = tv_first_name.text.toString() ?: "n/a",
                        lastName = tv_last_name.text.toString() ?: "n/a",
                        age = tv_age.text.toString().toInt() ?: 0
                    )
                )
            }

            /**
             * Create DB Entry using Room
             * */
            btn_create_room.id -> {
                mTestEntryViewModel.createTestEntry(
                    createTestEntryForRoom(
                        id = entryId++,
                        firstName = tv_first_name.text.toString() ?: "n/a",
                        lastName = tv_last_name.text.toString() ?: "n/a",
                        age = tv_age.text.toString().toInt() ?: 0
                    )
                )
            }
        }
    }


}

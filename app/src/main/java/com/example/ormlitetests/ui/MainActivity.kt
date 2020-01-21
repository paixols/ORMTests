package com.example.ormlitetests.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ormlitetests.R
import com.example.ormlitetests.app.DbTestApp
import com.example.ormlitetests.db.managers.TestTableManager
import com.example.ormlitetests.db.tables.TestTable
import com.j256.ormlite.dao.Dao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    /**
     * Properties
     * */
    private var entryId: Long = 0

    /**
     * LifeCycle
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
    }

    /*
    * UI
    * */
    private fun setupUi() {
        btn_create.setOnClickListener(this)
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

    private fun createTestEntry(
        id: Long,
        firstName: String,
        lastName: String,
        age: Int
    ): TestTable {
        return TestTable(id, firstName, lastName, age)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            /*
            * Create DB Entry
            * */
            btn_create.id -> {
                createEntryInTestTable(
                    createTestEntry(
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

package com.example.ormlitetests.db.managers

import android.util.Log
import com.example.ormlitetests.db.tables.TestTable
import com.j256.ormlite.dao.Dao
import java.sql.SQLException

class TestTableManager {

    object Holder {
        val instance: TestTableManager = TestTableManager()
    }

    companion object {
        val instance: TestTableManager by lazy { Holder.instance }
    }

    fun createEntryInTable(
        dao: Dao<TestTable, Int>,
        testTable: TestTable
    ): Pair<Boolean, Boolean>? {
        var status: Pair<Boolean, Boolean>? = null
        try {
            val daoStatus = dao.createOrUpdate(testTable)
            status = Pair(first = daoStatus.isCreated, second = daoStatus.isUpdated)
            Log.d("TestTableCreated: ", daoStatus.isCreated.toString())
            Log.d("TestTableUpdated: ", daoStatus.isUpdated.toString())
            Log.d("TestTableNlc: ", daoStatus.numLinesChanged.toString())
        } catch (e: SQLException) {
            Log.d("Database Exception", "error initializing test table")
        }
        return status
    }

}
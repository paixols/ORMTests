package com.example.ormlitetests.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.ormlitetests.db.tables.BaseTable
import com.example.ormlitetests.db.tables.TestTable
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import java.sql.SQLException

class Database(
    context: Context,
    dbName: String,
    cursorFactory: SQLiteDatabase.CursorFactory?,
    databaseVersion: Int
) : OrmLiteSqliteOpenHelper(
    context, dbName, cursorFactory, databaseVersion
) {

    private val tableClassList: MutableList<Class<out BaseTable>> = mutableListOf()

    init {
        tableClassList.add(
            TestTable::class.java
        )
    }

    override fun onCreate(p0: SQLiteDatabase?, p1: ConnectionSource?) {
        createTableIfNotExist()
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: ConnectionSource?, p2: Int, p3: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun createTableIfNotExist() {
        try {
            for (element in tableClassList) {
                TableUtils.createTableIfNotExists(
                    connectionSource,
                    element
                )
            }
        } catch (e: SQLException) {
            throw RuntimeException(e.localizedMessage)
        }
    }

}
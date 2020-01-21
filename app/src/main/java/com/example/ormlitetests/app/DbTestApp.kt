package com.example.ormlitetests.app

import android.app.Application
import android.util.Log
import com.example.ormlitetests.db.connectors.DatabaseConnector
import com.example.ormlitetests.db.connectors.DatabaseConnectorImpl
import com.example.ormlitetests.db.managers.DatabaseManager
import com.example.ormlitetests.db.managers.TestTableManager
import com.example.ormlitetests.db.tables.TestTable
import com.j256.ormlite.dao.Dao

/**
 * Application class to initialize DB on launch
 * */
class DbTestApp : Application() {

    private lateinit var dbConnector: DatabaseConnector

    object Holder {
        val instance: DbTestApp = DbTestApp()
    }

    companion object {
        val instance: DbTestApp by lazy { Holder.instance }
    }

    override fun onCreate() {
        super.onCreate()
        createDatabase()
    }

    private fun createDatabase() {
        dbConnector = getDbConnector()
        DatabaseManager.instance.getDatabase(this)
        DatabaseManager.instance.setDbConnector(dbConnector)
    }

    fun getDbConnector(): DatabaseConnector {
        if (!::dbConnector.isInitialized) {
            dbConnector = DatabaseConnectorImpl()
        }
        return dbConnector
    }

}
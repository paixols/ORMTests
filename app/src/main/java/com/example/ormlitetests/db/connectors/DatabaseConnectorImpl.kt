package com.example.ormlitetests.db.connectors

import com.example.ormlitetests.db.managers.DatabaseManager
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.stmt.query.In

class DatabaseConnectorImpl:
    DatabaseConnector {
    override fun <T> getDatabaseDao(clazz: Class<T>): Dao<*, Int> = DatabaseManager.instance.createOrGetDaoThrowable<Dao<*, Int>>(clazz)

}
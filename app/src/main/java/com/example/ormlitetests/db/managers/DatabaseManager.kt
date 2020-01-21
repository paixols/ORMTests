package com.example.ormlitetests.db.managers

import android.content.Context
import com.example.ormlitetests.db.Database
import com.example.ormlitetests.db.connectors.DatabaseConnector
import com.j256.ormlite.dao.Dao
import java.sql.SQLException

class DatabaseManager {

    private lateinit var databaseConnector: DatabaseConnector
    private var tablesMap = hashMapOf<Class<*>, Dao<*, Int>>()
    private lateinit var database: Database
    private val dbName: String = "dbTests_1"
    private val dbVersion: Int = 1

    object Holder {
        val instance: DatabaseManager =
            DatabaseManager()
    }

    companion object {
        val instance: DatabaseManager by lazy { Holder.instance }
    }

    fun setDbConnector(databaseConnector: DatabaseConnector) {
        instance.databaseConnector = databaseConnector
    }

    fun getDatabase(
        context: Context
    ): Database {
        if (!::database.isInitialized) {
            database = Database(
                context = context,
                dbName = this.dbName,
                cursorFactory = null,
                databaseVersion = this.dbVersion
            )
        }
        return database
    }

    @Throws(SQLException::class)
    fun <T> createOrGetDaoThrowable(clazz: Class<*>): Dao<*, Int> {
        return if (!tablesMap.containsKey(clazz)) {
            val dao: Dao<T, Int> = database.getDao(clazz) as Dao<T, Int>
            tablesMap[clazz] = dao
            dao as Dao<*, Int>
        } else {
            tablesMap[clazz]!!
        }
    }

}
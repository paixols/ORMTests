package com.example.ormlitetests.db.managers

import android.content.Context
import com.example.ormlitetests.BuildConfig
import com.example.ormlitetests.db.ORMLiteTestDatabase
import com.example.ormlitetests.db.connectors.DatabaseConnector
import com.j256.ormlite.dao.Dao
import java.sql.SQLException

class DatabaseManager {

    private lateinit var databaseConnector: DatabaseConnector
    private var tablesMap = hashMapOf<Class<*>, Dao<*, Int>>()
    private lateinit var ORMLiteTestDatabase: ORMLiteTestDatabase

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
    ): ORMLiteTestDatabase {
        if (!::ORMLiteTestDatabase.isInitialized) {
            ORMLiteTestDatabase = ORMLiteTestDatabase(
                context = context,
                dbName = BuildConfig.DB_NAME,
                cursorFactory = null,
                databaseVersion = BuildConfig.VERSION_CODE
            )
        }
        return ORMLiteTestDatabase
    }

    @Throws(SQLException::class)
    fun <T> createOrGetDaoThrowable(clazz: Class<*>): Dao<*, Int> {
        return if (!tablesMap.containsKey(clazz)) {
            val dao: Dao<T, Int> = ORMLiteTestDatabase.getDao(clazz) as Dao<T, Int>
            tablesMap[clazz] = dao
            dao as Dao<*, Int>
        } else {
            tablesMap[clazz]!!
        }
    }

}
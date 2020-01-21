package com.example.ormlitetests.db.connectors

import com.j256.ormlite.dao.Dao

/**
 *
 * */
interface DatabaseConnector {
    fun <T> getDatabaseDao(clazz: Class<T>): Dao<*, Int>?
}
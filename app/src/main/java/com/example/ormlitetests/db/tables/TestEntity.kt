package com.example.ormlitetests.db.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "testTable")
class TestEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "generated_id")
    var generated_id: Long = 0,

    @ColumnInfo(name = "firstName")
    var firstName: String? = null,

    @ColumnInfo(name = "lastName")
    var lastName: String? = null,

    @ColumnInfo(name = "age")
    val age: Int? = null
)
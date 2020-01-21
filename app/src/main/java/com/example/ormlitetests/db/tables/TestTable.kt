package com.example.ormlitetests.db.tables

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "testTable")
class TestTable(
    @DatabaseField(id = true)
    var generated_id: Long = 0,
    @DatabaseField
    var firstName: String? = null,
    @DatabaseField
    var lastName: String? = null,
    @DatabaseField
    var age: Int? = null
) : BaseTable
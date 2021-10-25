package com.marcelo.crudroom.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriber")
data class Subscriber(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subscriber_id")
    val id: Int,

    @ColumnInfo(name = "subscriber_name")
    val name: String,

    @ColumnInfo(name = "subscriber_email")
    val email: String
)
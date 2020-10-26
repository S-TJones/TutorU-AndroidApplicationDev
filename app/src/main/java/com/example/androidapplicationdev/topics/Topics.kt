package com.example.androidapplicationdev.topics

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topic_table")
data class Topics(
    // Each property in the class represents a column in the table.
    @PrimaryKey(autoGenerate = true)    val id: Int,
    @ColumnInfo(name = "topic_name")    val title: String,
    @ColumnInfo(name = "days")          val days: String?,
    @ColumnInfo(name = "time_start")    val time: String?,
    @ColumnInfo(name = "level")         val level: Int?,
    @ColumnInfo(name = "duration")      val length: Int
)

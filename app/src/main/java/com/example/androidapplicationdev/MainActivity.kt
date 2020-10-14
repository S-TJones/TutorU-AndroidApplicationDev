package com.example.androidapplicationdev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adds the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = TopicListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}


@Entity(tableName = "topic_table")
data class Topics(

    @PrimaryKey(autoGenerate = true)    val id: Int,
    @ColumnInfo(name = "topic_name")    val title: String,
    @ColumnInfo(name = "days")          val days: String?,
    @ColumnInfo(name = "time_start")    val time: String?,
    @ColumnInfo(name = "level")         val level: Int?,
    @ColumnInfo(name = "duration")      val length: Int

)
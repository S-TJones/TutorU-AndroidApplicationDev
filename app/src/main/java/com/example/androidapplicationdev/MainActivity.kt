package com.example.androidapplicationdev

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.sql.Time

class MainActivity : AppCompatActivity() {

    private lateinit var topicViewModel: TopicViewModel
    private val newTopicActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adds the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = TopicListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        topicViewModel = ViewModelProvider(this).get(TopicViewModel::class.java)
        topicViewModel.allTopics.observe(this, Observer { topics -> topics?.let { adapter.setTopics(it)}
            // Update the cached copy of the words in the adapter.
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewTopicActivity::class.java)
            startActivityForResult(intent, newTopicActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newTopicActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewTopicActivity.EXTRA_REPLY)?.let {
                val topic = Topics(0, it, "Friday", "12:00 p.m.", 1, 10)
                topicViewModel.insert(topic)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
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
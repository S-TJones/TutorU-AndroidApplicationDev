package com.example.androidapplicationdev.topics

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.androidapplicationdev.R

class NewTopicActivity : AppCompatActivity() {

    private lateinit var editTopicView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_topic)
        editTopicView = findViewById(R.id.edit_topic)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTopicView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val topic = editTopicView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, topic)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.topiclistsql.REPLY"
    }
}
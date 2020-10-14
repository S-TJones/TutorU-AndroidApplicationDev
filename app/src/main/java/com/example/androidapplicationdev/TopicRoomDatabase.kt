package com.example.androidapplicationdev

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Topic class
@Database(entities = arrayOf(Topics::class), version = 1, exportSchema = false)
public abstract class TopicRoomDatabase : RoomDatabase() {

    abstract fun topicDao(): TopicDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: TopicRoomDatabase? = null

        fun getDatabase(context: Context): TopicRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TopicRoomDatabase::class.java,
                    "topic_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
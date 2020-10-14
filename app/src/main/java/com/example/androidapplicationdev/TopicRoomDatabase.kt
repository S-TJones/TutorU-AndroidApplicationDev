package com.example.androidapplicationdev

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Topic class
@Database(entities = arrayOf(Topics::class), version = 1, exportSchema = false)
abstract class TopicRoomDatabase : RoomDatabase() {

    abstract fun topicDao(): TopicDao

    private class TopicDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database -> scope.launch {
                    val topicDao = database.topicDao()

                    // Delete all content here.
                    topicDao.deleteAll()

                    // Add sample topics.
                    val topic1 = Topics(
                        id = 1,
                        title = "Introductions",
                        days = "Monday",
                        time = "7:30 a.m.",
                        level = 1,
                        length = 40
                    )
                    topicDao.insert(topic1)

                    val topic2 = Topics(
                        id = 2, title = "Talk about School", days = "Thursday",
                        time = "8:00 a.m.", level = 1, length = 25
                    )
                    topicDao.insert(topic2)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: TopicRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): TopicRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TopicRoomDatabase::class.java,
                    "topic_database"
                )
                    .addCallback(TopicDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
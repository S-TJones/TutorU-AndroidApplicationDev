package com.example.androidapplicationdev.topics

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TopicDao {

    @Query("SELECT topic_name from topic_table ORDER BY topic_name ASC")
    fun getAlphabetizedWords(): LiveData<List<Topics>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(topic: Topics)

    @Query("DELETE FROM topic_table")
    suspend fun deleteAll()
}
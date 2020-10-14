package com.example.androidapplicationdev

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class TopicRepository(private val topicDao: TopicDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Topics>> = topicDao.getAlphabetizedWords()

    suspend fun insert(topic: Topics) {
        topicDao.insert(topic)
    }
}
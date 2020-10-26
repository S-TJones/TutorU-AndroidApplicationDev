package com.example.androidapplicationdev.topics

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapplicationdev.R

class TopicListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<TopicListAdapter.TopicViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var topics = emptyList<Topics>() // Cached copy of topics

    inner class TopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val topicItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return TopicViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val current = topics[position]
        holder.topicItemView.text = current.title
    }

    internal fun setTopics(topics: List<Topics>) {
        this.topics = topics
        notifyDataSetChanged()
    }

    override fun getItemCount() = topics.size
}
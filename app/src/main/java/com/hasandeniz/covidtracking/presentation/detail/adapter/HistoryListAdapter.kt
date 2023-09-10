package com.hasandeniz.covidtracking.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hasandeniz.covidtracking.databinding.HistoryListItemBinding
import com.hasandeniz.covidtracking.domain.model.History

class HistoryListAdapter :
    ListAdapter<History, HistoryListAdapter.HistoryViewHolder>(HistoryDiffUtil()) {
    var isItemClicked: ((History) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryViewHolder {
        val binding =
            HistoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        getItem(position)?.let { history ->
            val binding = holder.binding
            binding.cvHistory.setOnClickListener {
                isItemClicked?.invoke(history)
            }
            holder.bind(history)
        }
    }

    class HistoryViewHolder(val binding: HistoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: History) {
            binding.date = item.time
        }
    }

    class HistoryDiffUtil : DiffUtil.ItemCallback<History>() {
        override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem.country == newItem.country
        }

        override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem == newItem
        }
    }

}
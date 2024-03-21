package com.example.gethub.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gethub.domain.model.RepositoryModel
import com.example.gethub.databinding.ItemListRepositoryBinding
import com.example.gethub.util.ChipLoader.addChip
import com.example.gethub.util.DateFormatter.getTimeAgo
import com.example.gethub.util.DetailLanguage.setLeftDrawableColor

class RepositoryAdapter(private val onItemClicked: () -> Unit): ListAdapter<RepositoryModel, RepositoryAdapter.RepositoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryAdapter.RepositoryViewHolder {
        val binding = ItemListRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryAdapter.RepositoryViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(repo)
    }

    inner class RepositoryViewHolder(private val binding: ItemListRepositoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: RepositoryModel) {
            binding.apply {
                tvRepositoryName.text = repository.name
                tvRepositoryDescription.text = repository.description
                tvUpdatedAt.text = repository.updatedAt?.getTimeAgo()
                tvStarsCount.text = repository.stargazersCount.toString()
                tvForksCount.text = repository.forksCount.toString()
                tvRepositoryLanguage.setLeftDrawableColor(itemView.context, repository.language)
                tvRepositoryLanguage.text = repository.language ?: "Unknown"
                cgTopics.removeAllViews()
                repository.topics?.take(3)?.forEach { label ->
                    cgTopics.addChip(itemView.context, label.toString())
                }
            }

            itemView.setOnClickListener {
                onItemClicked()
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RepositoryModel>() {
            override fun areItemsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel) =
                oldItem == newItem
        }
    }
}
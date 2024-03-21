package com.example.gethub.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gethub.domain.model.UserModel
import com.example.gethub.databinding.ItemListUserBinding
import com.example.gethub.util.ColorType.setColor

class UserAdapter (private val onItemClicked: (String?) -> Unit): ListAdapter<UserModel, UserAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val binding = ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    inner class ViewHolder(private val binding: ItemListUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserModel) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(user.avatarUrl)
                    .into(ivUser)
                tvUsername.text = user.username
                tvUserType.setColor(itemView.context, user.type)
            }
            itemView.setOnClickListener { onItemClicked(user.username) }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserModel>() {
            override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel) =
                oldItem.username == newItem.username

            override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel) =
                oldItem == newItem
        }
    }
}
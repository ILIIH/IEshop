package com.example.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.product
import com.example.main.databinding.OtherItemBinding

class ProductAdapter : ListAdapter<product, ProductAdapter.RepoViewHolder>(DiffCallback()) {

    val category: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder.from(parent, category)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    class RepoViewHolder private constructor(val binding: OtherItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: product) {
            Glide.with(binding.root.context).load(item.photos.first()).into(binding.itemIcon)
            binding.title.text = item.type
            binding.secondTitle.text = "$" + item.cost.toString()
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup, category: Boolean): RepoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                return RepoViewHolder(OtherItemBinding.inflate(layoutInflater, parent, false))
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<product>() {
    override fun areItemsTheSame(oldItem: product, newItem: product): Boolean {
        return oldItem.name == newItem.name
    }
    override fun areContentsTheSame(oldItem: product, newItem: product): Boolean {
        return oldItem == newItem
    }
}

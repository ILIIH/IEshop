package com.example.core_ui.adapters.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.product
import com.example.core_ui.databinding.OtherItemBinding
import com.example.core_ui.databinding.SecondItemLayoutBinding


class ProductAdapter : ListAdapter<product, RecyclerView.ViewHolder>(DiffCallback()) {

    private var firstLayout: Boolean = false

    fun setRecyclerViewLayout(){
        firstLayout = true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerView.ViewHolder {
        return if(!firstLayout) FirstLayoutHolder.from(parent)
        else SecondLayoutHolder.from(parent)
    }

    override fun onBindViewHolder(holder:  RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when(holder) {
            is SecondLayoutHolder ->item?.let { holder.bind(it) }
            is FirstLayoutHolder -> item?.let { holder.bind(it) }
        }

    }

    class FirstLayoutHolder private constructor(val binding: OtherItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: product) {
            Glide.with(binding.root.context).load(item.photos.first()).into(binding.itemIcon)
            binding.title.text = item.name
            binding.secondTitle.text = "$" + item.cost.toString()
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): FirstLayoutHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                return FirstLayoutHolder(OtherItemBinding.inflate(layoutInflater, parent, false))
            }
        }
    }

    class SecondLayoutHolder private constructor(val binding: SecondItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: product) {
            Glide.with(binding.root.context).load(item.photos.first()).into(binding.itemIcon)
            if(item.ownerLogin != "C"){
            binding.category.text = item.name
             binding.categoryCount.text = "$" + item.cost.toString()

            }
            else {
                binding.category.text = item.type
                binding.categoryCount.text = item.cost.toString()+"+ Products"

            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SecondLayoutHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                return SecondLayoutHolder(SecondItemLayoutBinding.inflate(layoutInflater, parent, false))
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

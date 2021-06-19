package com.example.a2021_androidproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a2021_androidproject.databinding.ItemResBinding
import com.example.a2021_androidproject.model.Restaurant


class ResAdapter: ListAdapter<Restaurant,ResAdapter.ResItemViewHolder>(diffUtil) {
    inner class ResItemViewHolder(private val binding : ItemResBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(ResModel: Restaurant){
            binding.resName.text = ResModel.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResItemViewHolder {
        return ResItemViewHolder(ItemResBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ResItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object :DiffUtil.ItemCallback<Restaurant>(){
            override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}
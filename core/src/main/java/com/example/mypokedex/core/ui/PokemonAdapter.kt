package com.example.mypokedex.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.core.databinding.ItemPokemonBinding
import com.example.mypokedex.core.domain.model.Pokemon
import java.util.*

class PokemonAdapter(val onItemClick: (Pokemon) -> Unit) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private var listData = ArrayList<Pokemon>()

    fun setData(newListData: List<Pokemon>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        val itemBinding = holder.binding as ItemPokemonBinding

        itemBinding.root.setOnClickListener { onItemClick(data) }
        itemBinding.pokemon = data
        itemBinding.executePendingBindings()
    }

    override fun getItemCount(): Int = listData.size
}
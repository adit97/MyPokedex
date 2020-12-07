package com.example.mypokedex.core.utils.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.core.domain.model.Pokemon
import com.example.mypokedex.core.ui.PokemonAdapter

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, viewAdapter: RecyclerView.Adapter<*>) {
    with(view) {
        layoutManager = GridLayoutManager(context, 2)
        setHasFixedSize(true)
        adapter = viewAdapter
    }
}

@BindingAdapter("adapterPokemonList")
fun bindAdapterPokemonList(view: RecyclerView, pokemonList: List<Pokemon>?) {
    (view.adapter as? PokemonAdapter)?.setData(pokemonList)
}


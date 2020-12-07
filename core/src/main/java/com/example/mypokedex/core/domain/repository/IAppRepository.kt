package com.example.mypokedex.core.domain.repository

import com.example.mypokedex.core.data.Resource
import com.example.mypokedex.core.domain.model.Pokemon
import com.example.mypokedex.core.domain.model.PokemonDetail
import kotlinx.coroutines.flow.Flow

interface IAppRepository {
    fun getAllPokemon(query: String) : Flow<Resource<List<Pokemon>>>

    fun getAllFavoritePokemon() : Flow<List<Pokemon>>

    fun getDetailPokemon(pokemonName: String) : Flow<Resource<PokemonDetail>>

    fun setFavoritePokemon(pokemon: Pokemon, isFavorite: Boolean)
}
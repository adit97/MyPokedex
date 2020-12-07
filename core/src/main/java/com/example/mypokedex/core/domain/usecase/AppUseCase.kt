package com.example.mypokedex.core.domain.usecase

import com.example.mypokedex.core.data.Resource
import com.example.mypokedex.core.domain.model.Pokemon
import com.example.mypokedex.core.domain.model.PokemonDetail
import kotlinx.coroutines.flow.Flow

interface AppUseCase {
    fun getAllPokemon(query: String) : Flow<Resource<List<Pokemon>>>

    fun getAllFavoritePokemon() : Flow<List<Pokemon>>

    fun setFavoritePokemon(pokemon: Pokemon, isFavorite: Boolean)

    fun getPokemonDetail(pokemonName: String): Flow<Resource<PokemonDetail>>
}
package com.example.mypokedex.core.domain.usecase

import com.example.mypokedex.core.data.Resource
import com.example.mypokedex.core.domain.model.Pokemon
import com.example.mypokedex.core.domain.model.PokemonDetail
import com.example.mypokedex.core.domain.repository.IAppRepository
import kotlinx.coroutines.flow.Flow

class AppInteractor(private val appRepository: IAppRepository) : AppUseCase {
    override fun getAllPokemon(query: String): Flow<Resource<List<Pokemon>>> =
        appRepository.getAllPokemon(query)

    override fun getAllFavoritePokemon(): Flow<List<Pokemon>> =
        appRepository.getAllFavoritePokemon()

    override fun setFavoritePokemon(pokemon: Pokemon, isFavorite: Boolean) =
        appRepository.setFavoritePokemon(pokemon, isFavorite)

    override fun getPokemonDetail(pokemonName: String): Flow<Resource<PokemonDetail>> =
        appRepository.getDetailPokemon(pokemonName)

}
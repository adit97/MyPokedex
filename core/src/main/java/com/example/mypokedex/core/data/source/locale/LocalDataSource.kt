package com.example.mypokedex.core.data.source.locale

import com.example.mypokedex.core.data.source.locale.entity.PokemonDetailEntity
import com.example.mypokedex.core.data.source.locale.entity.PokemonEntity
import com.example.mypokedex.core.data.source.locale.room.AppDao
import kotlinx.coroutines.flow.Flow
class LocalDataSource (private val appDao: AppDao) {
    suspend fun insertPokemon(listPokemon: List<PokemonEntity>) = appDao.insertPokemon(listPokemon)

    fun getAllPokemon(query: String): Flow<List<PokemonEntity>> = appDao.getAllPokemon("%$query%")

    fun getAllFavoritePokemon(): Flow<List<PokemonEntity>> = appDao.getAllFavoritePokemon()

    fun setFavoritePokemon(pokemon: PokemonEntity, isFavorite: Boolean) {
        pokemon.pokemonIsFavorite = isFavorite
        appDao.updatePokemon(pokemon)
    }

    suspend fun insertPokemonDetail(pokemonDetail: PokemonDetailEntity) =
        appDao.insertPokemonDetail(pokemonDetail)

    fun getPokemonDetail(pokemonName: String): Flow<PokemonDetailEntity> =
        appDao.getPokemonDetail(pokemonName)
}
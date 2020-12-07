package com.example.mypokedex.core.data.source.locale.room

import androidx.room.*
import com.example.mypokedex.core.data.source.locale.entity.PokemonDetailEntity
import com.example.mypokedex.core.data.source.locale.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(listPokemon: List<PokemonEntity>)

    @Query("SELECT * FROM pokemon WHERE pokemon_name LIKE :query")
    fun getAllPokemon(query: String): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemon WHERE pokemon_is_favorite = 1")
    fun getAllFavoritePokemon(): Flow<List<PokemonEntity>>

    @Update
    fun updatePokemon(pokemon: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonDetail(pokemonDetail: PokemonDetailEntity)

    @Query("SELECT * FROM pokemon_detail WHERE pokemon_name = :pokemonName")
    fun getPokemonDetail(pokemonName: String): Flow<PokemonDetailEntity>

}
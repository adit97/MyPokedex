package com.example.mypokedex.core.data

import com.example.mypokedex.core.data.source.locale.LocalDataSource
import com.example.mypokedex.core.data.source.remote.RemoteDataSource
import com.example.mypokedex.core.data.source.remote.network.ApiResponse
import com.example.mypokedex.core.data.source.remote.response.detail.PokemonDetailResponse
import com.example.mypokedex.core.data.source.remote.response.pokemon.PokemonResponse
import com.example.mypokedex.core.domain.model.Pokemon
import com.example.mypokedex.core.domain.model.PokemonDetail
import com.example.mypokedex.core.domain.repository.IAppRepository
import com.example.mypokedex.core.utils.AppExecutors
import com.example.mypokedex.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IAppRepository {

    override fun getAllPokemon(query: String): Flow<Resource<List<Pokemon>>> =
        object : NetworkBoundResource<List<Pokemon>, PokemonResponse>(appExecutors) {
            override fun loadFromDb(): Flow<List<Pokemon>> = localDataSource.getAllPokemon(query).map {
                DataMapper.mapPokemonEntitiesToPokemon(it)
            }

            override fun shouldFetch(data: List<Pokemon>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<PokemonResponse>> =
                remoteDataSource.fetchPokemonList()

            override suspend fun saveCallResult(data: PokemonResponse) {
                val pokemonList = DataMapper.mapPokemonResponsesToPokemonEntities(data)
                localDataSource.insertPokemon(pokemonList)
            }
        }.asFlow()

    override fun getAllFavoritePokemon(): Flow<List<Pokemon>> {
        return localDataSource.getAllFavoritePokemon().map {
            DataMapper.mapPokemonEntitiesToPokemon(it)
        }
    }

    override fun getDetailPokemon(pokemonName: String): Flow<Resource<PokemonDetail>> {
        return object : NetworkBoundResource<PokemonDetail, PokemonDetailResponse>(appExecutors) {
            override fun loadFromDb(): Flow<PokemonDetail> {
                return localDataSource.getPokemonDetail(pokemonName).map {
                    DataMapper.mapPokemonDetailEntitiesToPokemonDetail(it)
                }
            }

            override fun shouldFetch(data: PokemonDetail?): Boolean =
                data == null || data.pokemonDetailId == 0

            override suspend fun createCall(): Flow<ApiResponse<PokemonDetailResponse>> =
                remoteDataSource.fetchPokemonDetail(pokemonName)

            override suspend fun saveCallResult(data: PokemonDetailResponse) {
                val pokemonDetail =
                    DataMapper.mapPokemonDetailResponsesToPokemonDetailEntities(data)

                localDataSource.insertPokemonDetail(pokemonDetail)
            }
        }.asFlow()
    }

    override fun setFavoritePokemon(pokemon: Pokemon, isFavorite: Boolean) {
        val pokemonEntity = DataMapper.mapPokemonToPokemonEntity(pokemon)
        appExecutors.diskIO()
            .execute { localDataSource.setFavoritePokemon(pokemonEntity, isFavorite) }
    }

}
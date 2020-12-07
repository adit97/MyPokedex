package com.example.mypokedex.core.data.source.remote

import com.example.mypokedex.core.data.source.remote.network.ApiResponse
import com.example.mypokedex.core.data.source.remote.network.ApiService
import com.example.mypokedex.core.data.source.remote.response.detail.PokemonDetailResponse
import com.example.mypokedex.core.data.source.remote.response.pokemon.PokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun fetchPokemonList(): Flow<ApiResponse<PokemonResponse>> {
        return flow {
            try {
                val response = apiService.fetchPokemonList()
                val data = response.results
                if (data.isEmpty()) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Timber.e("Error API : $e")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchPokemonDetail(name: String): Flow<ApiResponse<PokemonDetailResponse>> {
        return flow {
            try {
                val response: PokemonDetailResponse = apiService.fetchPokemonDetail(name = name)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Timber.e("Error API : $e")
            }
        }.flowOn(Dispatchers.IO)
    }


}
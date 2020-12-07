package com.example.mypokedex.core.data.source.remote.network

import com.example.mypokedex.core.data.source.remote.response.detail.PokemonDetailResponse
import com.example.mypokedex.core.data.source.remote.response.pokemon.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon?limit=893")
    suspend fun fetchPokemonList(): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun fetchPokemonDetail(@Path("name") name: String): PokemonDetailResponse
}
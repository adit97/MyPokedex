package com.example.mypokedex.core.data.source.remote.response.pokemon

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("next")
    val next: String,

    @field:SerializedName("previous")
    val previous: String,

    @field:SerializedName("results")
    val results: List<Result>
)
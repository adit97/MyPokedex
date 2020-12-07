package com.example.mypokedex.core.data.source.remote.response.detail

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @field:SerializedName("abilities")
    val abilities: List<Ability>,

    @field:SerializedName("base_experience")
    val baseExperience: Int,

    @field:SerializedName("height")
    val height: Int,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("stats")
    val stats: List<Stat>,

    @field:SerializedName("types")
    val types: List<Type>,

    @field:SerializedName("weight")
    val weight: Int
)
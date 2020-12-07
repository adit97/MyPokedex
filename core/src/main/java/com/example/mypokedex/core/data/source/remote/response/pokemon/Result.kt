package com.example.mypokedex.core.data.source.remote.response.pokemon

import com.google.gson.annotations.SerializedName

data class Result(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("url")
    val url: String
)
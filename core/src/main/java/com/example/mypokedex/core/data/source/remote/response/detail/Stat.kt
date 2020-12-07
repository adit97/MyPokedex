package com.example.mypokedex.core.data.source.remote.response.detail

import com.google.gson.annotations.SerializedName

data class Stat(
    @field:SerializedName("base_stat")
    val baseStat: Int
)
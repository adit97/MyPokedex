package com.example.mypokedex.core.data.source.remote.response.detail

import com.google.gson.annotations.SerializedName

data class Type(
    @field:SerializedName("type")
    val type: TypeX
) {
    override fun toString(): String {
        return type.name
    }
}
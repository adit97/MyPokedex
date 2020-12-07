package com.example.mypokedex.core.data.source.remote.response.detail

import com.google.gson.annotations.SerializedName

data class Ability(
    @field:SerializedName("ability")
    val ability: AbilityX
) {
    override fun toString(): String {
        return ability.name
    }
}
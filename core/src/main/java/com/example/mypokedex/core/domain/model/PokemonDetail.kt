package com.example.mypokedex.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonDetail(
    val pokemonDetailId: Int = 0,
    val pokemonAbilities: String = "",
    val pokemonBaseExperience: Int = 0,
    val pokemonHeight: Int = 0,
    val pokemonName: String = "",
    val pokemonHp: Int = 0,
    val pokemonAttack: Int = 0,
    val pokemonDefense: Int = 0,
    val pokemonSpecialAttack: Int = 0,
    val pokemonSpecialDefense: Int = 0,
    val pokemonSpeed: Int = 0,
    val pokemonTypes: String = "",
    val pokemonWeight: Int = 0
) : Parcelable {
    fun getImageUrl(): String {
        return "https://pokeres.bastionbot.org/images/pokemon/$pokemonDetailId.png"
    }

    fun getPokemonIndex(): String {
        return if (pokemonDetailId < 100) {
            if (pokemonDetailId < 10) {
                "#00$pokemonDetailId"
            } else {
                "#0$pokemonDetailId"
            }
        } else {
            "#$pokemonDetailId"
        }
    }
}
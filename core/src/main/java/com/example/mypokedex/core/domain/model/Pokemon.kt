package com.example.mypokedex.core.domain.model

import android.os.Parcelable
import androidx.databinding.BaseObservable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    val pokemonId: Int,
    val pokemonName: String,
    val pokemonUrl: String,
    var pokemonIsFavorite: Boolean
) : BaseObservable(), Parcelable {
    fun getImageUrl(): String {
        val index = pokemonUrl.split("/".toRegex()).dropLast(1).last()
        return "https://pokeres.bastionbot.org/images/pokemon/$index.png"
    }
}
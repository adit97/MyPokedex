package com.example.mypokedex.core.data.source.locale.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey
    @ColumnInfo(name = "pokemon_id")
    val pokemonId: Int,

    @ColumnInfo(name = "pokemon_name")
    val pokemonName: String,

    @ColumnInfo(name = "pokemon_url")
    val pokemonUrl: String,

    @ColumnInfo(name = "pokemon_is_favorite")
    var pokemonIsFavorite: Boolean = false
)
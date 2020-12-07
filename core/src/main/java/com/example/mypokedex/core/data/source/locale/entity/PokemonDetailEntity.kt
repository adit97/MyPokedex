package com.example.mypokedex.core.data.source.locale.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_detail")
data class PokemonDetailEntity(
    @PrimaryKey
    @ColumnInfo(name = "pokemon_detail_id")
    val pokemonDetailId: Int,

    @ColumnInfo(name = "pokemon_abilities")
    val pokemonAbilities: String,

    @ColumnInfo(name = "pokemon_base_experience")
    val pokemonBaseExperience: Int,

    @ColumnInfo(name = "pokemon_height")
    val pokemonHeight: Int,

    @ColumnInfo(name = "pokemon_name")
    val pokemonName: String,

    @ColumnInfo(name = "pokemon_hp")
    val pokemonHp: Int,

    @ColumnInfo(name = "pokemon_attack")
    val pokemonAttack: Int,

    @ColumnInfo(name = "pokemon_defense")
    val pokemonDefense: Int,

    @ColumnInfo(name = "pokemon_special_attack")
    val pokemonSpecialAttack: Int,

    @ColumnInfo(name = "pokemon_special_defense")
    val pokemonSpecialDefense: Int,

    @ColumnInfo(name = "pokemon_speed")
    val pokemonSpeed: Int,

    @ColumnInfo(name = "pokemon_types")
    val pokemonTypes: String,

    @ColumnInfo(name = "pokemon_weight")
    val pokemonWeight: Int
) 
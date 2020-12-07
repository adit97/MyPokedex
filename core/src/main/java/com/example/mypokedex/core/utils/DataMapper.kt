package com.example.mypokedex.core.utils

import com.example.mypokedex.core.data.source.locale.entity.PokemonDetailEntity
import com.example.mypokedex.core.data.source.locale.entity.PokemonEntity
import com.example.mypokedex.core.data.source.remote.response.detail.PokemonDetailResponse
import com.example.mypokedex.core.data.source.remote.response.pokemon.PokemonResponse
import com.example.mypokedex.core.domain.model.Pokemon
import com.example.mypokedex.core.domain.model.PokemonDetail

object DataMapper {

    /*
    * Pokemon Model
    * */

    fun mapPokemonResponsesToPokemonEntities(input: PokemonResponse): List<PokemonEntity> {
        val pokemonList = mutableListOf<PokemonEntity>()

        input.results.map {
            pokemonList.add(
                PokemonEntity(
                    pokemonId = it.url.split("/".toRegex()).dropLast(1).last().toInt(),
                    pokemonName = it.name,
                    pokemonUrl = it.url
                )
            )
        }

        return pokemonList
    }

    fun mapPokemonEntitiesToPokemon(input: List<PokemonEntity>): List<Pokemon> =
        input.map {
            Pokemon(
                pokemonId = it.pokemonId,
                pokemonName = it.pokemonName,
                pokemonUrl = it.pokemonUrl,
                pokemonIsFavorite = it.pokemonIsFavorite
            )
        }

    fun mapPokemonToPokemonEntity(input: Pokemon) = PokemonEntity(
        pokemonId = input.pokemonId,
        pokemonName = input.pokemonName,
        pokemonUrl = input.pokemonUrl,
        pokemonIsFavorite = input.pokemonIsFavorite
    )

    /*
    * Pokemon Detail Model
    * */

    fun mapPokemonDetailResponsesToPokemonDetailEntities(input: PokemonDetailResponse) =
        PokemonDetailEntity(
            pokemonDetailId = input.id,
            pokemonName = input.name,
            pokemonAbilities = input.abilities.joinToString(),
            pokemonHp = input.stats[0].baseStat,
            pokemonAttack = input.stats[1].baseStat,
            pokemonDefense = input.stats[2].baseStat,
            pokemonSpecialAttack = input.stats[3].baseStat,
            pokemonSpecialDefense = input.stats[4].baseStat,
            pokemonSpeed = input.stats[5].baseStat,
            pokemonBaseExperience = input.baseExperience,
            pokemonHeight = input.height,
            pokemonTypes = input.types.joinToString(),
            pokemonWeight = input.weight
        )

    fun mapPokemonDetailEntitiesToPokemonDetail(input: PokemonDetailEntity?): PokemonDetail {
        return if (input != null) {
            PokemonDetail(
                pokemonDetailId = input.pokemonDetailId,
                pokemonName = input.pokemonName,
                pokemonAbilities = input.pokemonAbilities,
                pokemonHp = input.pokemonHp,
                pokemonAttack = input.pokemonAttack,
                pokemonDefense = input.pokemonDefense,
                pokemonSpecialAttack = input.pokemonSpecialAttack,
                pokemonSpecialDefense = input.pokemonSpecialDefense,
                pokemonSpeed = input.pokemonSpeed,
                pokemonBaseExperience = input.pokemonBaseExperience,
                pokemonHeight = input.pokemonHeight,
                pokemonTypes = input.pokemonTypes,
                pokemonWeight = input.pokemonWeight
            )
        }else {
            PokemonDetail()
        }
    }

}
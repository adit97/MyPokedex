package com.example.mypokedex.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mypokedex.core.domain.model.Pokemon
import com.example.mypokedex.core.domain.model.PokemonDetail
import com.example.mypokedex.core.domain.usecase.AppUseCase

class DetailViewModel(private val appUseCase: AppUseCase) :
    ViewModel() {

    val pokemonDetail = MutableLiveData<PokemonDetail>()
    val pokemon = MutableLiveData<Pokemon>()
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val isFavoritePokemon = MutableLiveData<Boolean>()

    fun pokemonDetail() =
        pokemon.value?.let { appUseCase.getPokemonDetail(it.pokemonName).asLiveData() }

    fun setFavorite() {
        isFavoritePokemon.value = !isFavoritePokemon.value!!
        appUseCase.setFavoritePokemon(pokemon.value!!, isFavoritePokemon.value!!)
    }
}

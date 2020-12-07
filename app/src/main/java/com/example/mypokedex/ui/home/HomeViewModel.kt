package com.example.mypokedex.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mypokedex.core.data.Resource
import com.example.mypokedex.core.domain.model.Pokemon
import com.example.mypokedex.core.domain.usecase.AppUseCase

class HomeViewModel(private val appUseCase: AppUseCase) : ViewModel() {

    val listPokemon = MutableLiveData<List<Pokemon>>()
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val query = MutableLiveData<String>()

    init {
        query.value = ""
    }

    fun fetchPokemonList(query: String): LiveData<Resource<List<Pokemon>>> {
        return appUseCase.getAllPokemon(query).asLiveData()
    }

}


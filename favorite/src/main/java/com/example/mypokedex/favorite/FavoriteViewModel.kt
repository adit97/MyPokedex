package com.example.mypokedex.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mypokedex.core.domain.usecase.AppUseCase

class FavoriteViewModel(appUseCase: AppUseCase) : ViewModel() {
    val listPokemon = appUseCase.getAllFavoritePokemon().asLiveData()
}
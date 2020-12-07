package com.example.mypokedex.di

import com.example.mypokedex.core.domain.usecase.AppInteractor
import com.example.mypokedex.core.domain.usecase.AppUseCase
import com.example.mypokedex.ui.detail.DetailViewModel
import com.example.mypokedex.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AppUseCase> { AppInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
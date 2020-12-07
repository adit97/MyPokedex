package com.example.mypokedex

import android.app.Application
import com.example.mypokedex.core.di.databaseModule
import com.example.mypokedex.core.di.networkModule
import com.example.mypokedex.core.di.repositoryModule
import com.example.mypokedex.di.useCaseModule
import com.example.mypokedex.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
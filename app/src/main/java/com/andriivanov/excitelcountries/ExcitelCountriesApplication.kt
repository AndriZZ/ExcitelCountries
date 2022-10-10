package com.andriivanov.excitelcountries

import android.app.Application
import com.andriivanov.excitelcountries.di.appModule
import com.andriivanov.excitelcountries.di.networkModule
import com.andriivanov.excitelcountries.di.repositoryModule
import com.andriivanov.excitelcountries.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ExcitelCountriesApplication : Application() {

    override fun onCreate() {
        super<Application>.onCreate()

        // setup di
        startKoin {
            androidLogger()
            androidContext(this@ExcitelCountriesApplication)

            koin.loadModules(
                listOf(
                    appModule,
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                )
            )
        }
    }
}
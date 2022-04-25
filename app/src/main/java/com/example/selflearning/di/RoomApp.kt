package com.example.selflearning.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RoomApp:Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RoomApp)
            modules(listOf(networkModule, viewModelModule))
        }
    }
}
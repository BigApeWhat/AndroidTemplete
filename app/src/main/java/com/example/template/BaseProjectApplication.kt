package com.example.template

import android.app.Application
import com.example.template.di.HomeModule
import com.example.template.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BaseProjectApplication)
            modules(
                    listOf(
                            HomeModule.mainModule,
                            NetworkModule.retrofitModule
                    )
            )
        }
    }
}
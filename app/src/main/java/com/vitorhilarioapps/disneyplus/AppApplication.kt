package com.vitorhilarioapps.disneyplus

import android.app.Application
import com.vitorhilarioapps.disneyplus.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(appModule)
        }
    }
}
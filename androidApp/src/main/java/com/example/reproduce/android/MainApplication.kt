package com.example.reproduce.android

import android.app.Application
import com.example.reproduce.DataRepository
import com.example.reproduce.core.AppStore
import com.example.reproduce.di.DatabaseFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module


class MainApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initKoin()
    }

    private val appModule = module {
        single<AppStore> { AppStore() }
        single<DataRepository> {
            DataRepository(
                factory = DatabaseFactory(INSTANCE),
            )
        }
    }

    private fun initKoin() {
        startKoin {
            if (BuildConfig.DEBUG) androidLogger(Level.ERROR)

            androidContext(this@MainApplication)
            modules(appModule)
        }
    }

    companion object {
        const val TAG = "MainApplication"
        internal lateinit var INSTANCE: MainApplication
            private set
    }
}

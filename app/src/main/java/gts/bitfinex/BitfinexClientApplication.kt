package gts.bitfinex

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

import org.koin.core.context.startKoin

import timber.log.Timber
import timber.log.Timber.DebugTree

class BitfinexClientApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()
            // use the Android context given there
            androidContext(this@BitfinexClientApplication)
            // module list
            modules(listOf(appModule))
        }
    }
}
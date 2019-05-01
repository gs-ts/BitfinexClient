package gts.bitfinex

import android.app.Application

import org.koin.android.ext.android.startKoin

import timber.log.Timber
import timber.log.Timber.DebugTree

class BitfinexClientApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        startKoin(this@BitfinexClientApplication, listOf(appModule))
    }
}
package gts.bitfinex

import android.app.Application

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.Lifecycle
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import com.tinder.scarlet.streamadapter.rxjava2.RxJava2StreamAdapterFactory

import gts.bitfinex.data.network.BitfinexApi
import gts.bitfinex.data.network.BitfinexRepository
import gts.bitfinex.domain.BitfinexService
import gts.bitfinex.domain.usecase.ObserveTickerUseCase
import gts.bitfinex.domain.usecase.ObserveOrderBookUseCase
import gts.bitfinex.presentation.ui.fragments.BitfinexViewModel

val appModule = module {
    single { createAndroidLifecycle(get()) }
    single { createOkHttpClient() }
    single { createScarlet(okHttpClient = get(), lifecycle = get()) }
    single<BitfinexService> { BitfinexRepository(bitfinexApi = get()) }

    factory { ObserveTickerUseCase(bitfinexService = get()) }
    factory { ObserveOrderBookUseCase(bitfinexService = get()) }
    viewModel { BitfinexViewModel(observeTickerUseCase = get(), observeOrderBookUseCase = get()) }
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
}

fun createAndroidLifecycle(application: Application): Lifecycle {
    return AndroidLifecycle.ofApplicationForeground(application)
}

private val jsonMoshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

fun createScarlet(okHttpClient: OkHttpClient, lifecycle: Lifecycle): BitfinexApi {
    return Scarlet.Builder()
        .webSocketFactory(okHttpClient.newWebSocketFactory(BitfinexApi.BASE_URI))
        .lifecycle(lifecycle)
        .addMessageAdapterFactory(MoshiMessageAdapter.Factory(jsonMoshi))
        .addStreamAdapterFactory(RxJava2StreamAdapterFactory())
        .build()
        .create()
}

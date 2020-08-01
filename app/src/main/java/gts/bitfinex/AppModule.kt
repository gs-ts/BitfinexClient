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

import gts.bitfinex.data.remote.BitfinexApi
import gts.bitfinex.data.remote.BitfinexClient
import gts.bitfinex.data.remote.BitfinexClientImpl
import gts.bitfinex.data.repository.BitfinexRepositoryImpl
import gts.bitfinex.domain.BitfinexRepository
import gts.bitfinex.domain.usecase.ObserveTickerUseCase
import gts.bitfinex.domain.usecase.ObserveOrderBookUseCase
import gts.bitfinex.presentation.ui.BitfinexViewModel

// declare a module
val appModule = module {
    // Define single instance of AndroidLifecycle
    // Resolve constructor dependencies with get(), here we need an Application object
    single { createAndroidLifecycle(application = get()) }
    // Define single instance of OkHttpClient
    single { createOkHttpClient() }
    // Define single instance of Scarlet
    // Resolve constructor dependencies with get(), here we need an OkHttpClient, and a lifecycle
    single { createScarlet(okHttpClient = get(), lifecycle = get()) }
    // Define single instance of type BitfinexDataSource
    // Resolve constructor dependencies with get(), here we need a BitfinexApi
    single<BitfinexClient> { BitfinexClientImpl(bitfinexApi = get()) }
    // Define single instance of type BitfinexService (infered parameter in <>)
    // Resolve constructor dependencies with get(), here we need the BitfinexApi
    single<BitfinexRepository> {
        BitfinexRepositoryImpl(
            bitfinexClient = get()
        )
    }

    // Define a factory (create a new instance each time) for ObserveTickerUseCase
    // Resolve constructor dependency with get(), here we need the BitfinexService
    factory { ObserveTickerUseCase(bitfinexRepository = get()) }
    // Define a factory (create a new instance each time) for ObserveOrderBookUseCase
    // Resolve constructor dependency with get(), here we need the BitfinexService
    factory { ObserveOrderBookUseCase(bitfinexRepository = get()) }

    // Define ViewModel and resolve constructor dependencies with get(),
    // here we need ObserveTickerUseCase, and ObserveOrderBookUseCase
    viewModel {
        BitfinexViewModel(
            observeTickerUseCase = get(),
            observeOrderBookUseCase = get()
        )
    }
}

private fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
}

private fun createAndroidLifecycle(application: Application): Lifecycle {
    return AndroidLifecycle.ofApplicationForeground(application)
}

private val jsonMoshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// A Retrofit inspired WebSocket client for Kotlin, Java, and Android, that supports websockets.
private fun createScarlet(okHttpClient: OkHttpClient, lifecycle: Lifecycle): BitfinexApi {
    return Scarlet.Builder()
        .webSocketFactory(okHttpClient.newWebSocketFactory(BitfinexApi.BASE_URI))
        .lifecycle(lifecycle)
        .addMessageAdapterFactory(MoshiMessageAdapter.Factory(jsonMoshi))
        .addStreamAdapterFactory(RxJava2StreamAdapterFactory())
        .build()
        .create()
}

package gts.bitfinex.data.remote

import android.annotation.SuppressLint

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

import com.tinder.scarlet.WebSocket

import gts.bitfinex.data.entity.SubscribeTickerRequest
import gts.bitfinex.data.entity.SubscribeOrderBookRequest

import timber.log.Timber

@SuppressLint("CheckResult")
class BitfinexClientImpl(private val bitfinexApi: BitfinexApi) : BitfinexClient {

    private val TICKER_SNAPSHOT_SIZE = 11 // https://docs.bitfinex.com/reference#ws-public-ticker
    private val ORDERBOOK_SNAPSHOT_SIZE = 4

    override fun subscribeTicker(subscribeTickerRequest: SubscribeTickerRequest): Flowable<Array<String>> {
        bitfinexApi.openWebSocketEvent()
            .filter {
                it is WebSocket.Event.OnConnectionOpened<*>
            }
            .subscribe({
                bitfinexApi.sendTickerRequest(subscribeTickerRequest)
            }, { e ->
                Timber.e(e)
            })

        return bitfinexApi.observeTicker()
            .subscribeOn(Schedulers.io())
            .filter { it.size == TICKER_SNAPSHOT_SIZE } // make sure it's a ticker
    }

    override fun subscribeOrderBook(subscribeOrderBookRequest: SubscribeOrderBookRequest): Flowable<DoubleArray> {
        bitfinexApi.openWebSocketEvent()
            .filter {
                it is WebSocket.Event.OnConnectionOpened<*>
            }
            .subscribe({
                bitfinexApi.sendOrderBookRequest(subscribeOrderBookRequest)
            }, { e ->
                Timber.e(e)
            })

        return bitfinexApi.observeOrderBook()
            .subscribeOn(Schedulers.io())
            .filter { it.size == ORDERBOOK_SNAPSHOT_SIZE } // make sure it's an order book
    }
}

package gts.bitfinex.data.network

import android.annotation.SuppressLint

import com.tinder.scarlet.WebSocket

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

import gts.bitfinex.data.model.toSubcribeTickerRequest
import gts.bitfinex.data.model.toSubcribeOrderBookrRequest

import gts.bitfinex.domain.BitfinexService
import gts.bitfinex.domain.model.TickerData
import gts.bitfinex.domain.model.toTickerData
import gts.bitfinex.domain.model.OrderBookData
import gts.bitfinex.domain.model.toOrderBookData
import gts.bitfinex.domain.entities.SubscribeTickerEntity
import gts.bitfinex.domain.entities.SubscribeOrderBookEntity

import timber.log.Timber

@SuppressLint("CheckResult")
class BitfinexRepository(private val bitfinexApi: BitfinexApi) : BitfinexService {

    private val TICKER_SNAPSHOT_SIZE = 11 // https://docs.bitfinex.com/reference#ws-public-ticker
    private val ORDERBOOK_SNAPSHOT_SIZE = 4

    override fun subscribeAndObserveTicker(subscribe: SubscribeTickerEntity): Flowable<TickerData> {
        bitfinexApi.openWebSocketEvent()
            .filter {
                it is WebSocket.Event.OnConnectionOpened<*>
            }
            .subscribe({
                bitfinexApi.sendTickerRequest(subscribe.toSubcribeTickerRequest())
            }, { e ->
                Timber.e(e)
            })

        return bitfinexApi.observeTicker()
            .subscribeOn(Schedulers.io())
            .filter { it.size == TICKER_SNAPSHOT_SIZE } // make sure it's a ticker
            .map { ticker -> ticker.toTickerData() }
    }

    override fun subscribeAndObserveOrderBook(subscribe: SubscribeOrderBookEntity): Flowable<OrderBookData> {
        bitfinexApi.openWebSocketEvent()
            .filter {
                it is WebSocket.Event.OnConnectionOpened<*>
            }
            .subscribe({
                bitfinexApi.sendOrderBookRequest(subscribe.toSubcribeOrderBookrRequest())
            }, { e ->
                Timber.e(e)
            })

        return bitfinexApi.observeOrderBook()
            .subscribeOn(Schedulers.io())
            .filter { it.size == ORDERBOOK_SNAPSHOT_SIZE } // make sure it's an order book
            .observeOn(Schedulers.computation())
            .map { orderBook -> orderBook.toOrderBookData() }
    }
}
package gts.bitfinex.data.remote

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

//    private lateinit var chanId: String

    override fun subscribeAndObserveTicker(subscribe: SubscribeTickerEntity): Flowable<TickerData> {
        Timber.d("===> subscribeAndObserveTicker")
        bitfinexApi.openWebSocketEvent()
            .filter {
                it is WebSocket.Event.OnConnectionOpened<*>
            }
            .subscribe({
                bitfinexApi.sendTickerRequest(subscribe.toSubcribeTickerRequest())
                Timber.d("subscribeAndObserveTicker <===")
            }, { e ->
                Timber.e(e)
            })

        return bitfinexApi.observeTicker()
            .subscribeOn(Schedulers.io())
            .map {
                    t -> t.toTickerData()
            }
    }

    override fun subscribeAndObserveOrderBook(subscribe: SubscribeOrderBookEntity): Flowable<OrderBookData> {
        Timber.d("===> subscribeAndObserveOrderBook")
        bitfinexApi.openWebSocketEvent()
            .filter {
                it is WebSocket.Event.OnConnectionOpened<*>
            }
            .subscribe({
                bitfinexApi.sendOrderBookRequest(subscribe.toSubcribeOrderBookrRequest())
                Timber.d("subscribeAndObserveOrderBook <===")
            }, { e ->
                Timber.e(e)
            })

        return bitfinexApi.observeOrderBook()
            .subscribeOn(Schedulers.io())
            .map { t -> t.toOrderBookData() }
    }

//    private fun receiveResponse() {
//        bitfinexApi.receiveResponse()
//            .subscribeOn(Schedulers.io())
//            .filter {
//                it.channel == subscribe.channel
//            }
//            .map { tickerResponse ->
//                tickerResponse.chanId
//            }
//            .subscribe ({
//                chanId = it
//                Timber.d("subscribeTicker - receiveResponse done: chanId = $it <===")
//            }, { e ->
//                Timber.e("subscribeTicker - receiveResponse error: $e <===")
//            })
//    }
}
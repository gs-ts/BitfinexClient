package gts.bitfinex.data.repository

import io.reactivex.Flowable

import gts.bitfinex.data.remote.BitfinexClient
import gts.bitfinex.data.entity.toSubcribeTickerRequest
import gts.bitfinex.data.entity.toSubcribeOrderBookrRequest

import gts.bitfinex.domain.BitfinexRepository
import gts.bitfinex.domain.model.TickerData
import gts.bitfinex.domain.model.toTickerData
import gts.bitfinex.domain.model.OrderBookData
import gts.bitfinex.domain.model.toOrderBookData
import gts.bitfinex.domain.entities.SubscribeTicker
import gts.bitfinex.domain.entities.SubscribeOrderBook

class BitfinexRepositoryImpl(private val bitfinexClient: BitfinexClient) : BitfinexRepository {

    override fun ObserveTicker(subscribeTicker: SubscribeTicker): Flowable<TickerData> {
        return bitfinexClient.subscribeTicker(subscribeTicker.toSubcribeTickerRequest())
            .map { response -> response.toTickerData() }
    }

    override fun ObserveOrderBook(subscribeOrderBook: SubscribeOrderBook): Flowable<OrderBookData> {
        return bitfinexClient.subscribeOrderBook(subscribeOrderBook.toSubcribeOrderBookrRequest())
            .map { response -> response.toOrderBookData() }
    }
}

package gts.bitfinex.domain

import io.reactivex.Flowable

import gts.bitfinex.domain.model.TickerData
import gts.bitfinex.domain.model.OrderBookData
import gts.bitfinex.domain.entities.SubscribeTicker
import gts.bitfinex.domain.entities.SubscribeOrderBook

interface BitfinexRepository {

    fun ObserveTicker(subscribeTicker: SubscribeTicker): Flowable<TickerData>

    fun ObserveOrderBook(subscribeOrderBook: SubscribeOrderBook): Flowable<OrderBookData>
}
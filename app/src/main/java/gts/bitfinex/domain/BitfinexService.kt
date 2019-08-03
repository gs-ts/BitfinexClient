package gts.bitfinex.domain

import io.reactivex.Flowable

import gts.bitfinex.domain.model.TickerData
import gts.bitfinex.domain.model.OrderBookData
import gts.bitfinex.domain.entities.SubscribeTicker
import gts.bitfinex.domain.entities.SubscribeOrderBook

interface BitfinexService {

    fun subscribeAndObserveTicker(subscribeTicker: SubscribeTicker): Flowable<TickerData>

    fun subscribeAndObserveOrderBook(subscribeOrderBook: SubscribeOrderBook): Flowable<OrderBookData>
}
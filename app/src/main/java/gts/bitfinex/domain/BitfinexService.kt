package gts.bitfinex.domain

import gts.bitfinex.domain.model.TickerData
import gts.bitfinex.domain.model.OrderBookData

import io.reactivex.Flowable

import gts.bitfinex.domain.entities.SubscribeTickerEntity
import gts.bitfinex.domain.entities.SubscribeOrderBookEntity

interface BitfinexService {

    fun subscribeAndObserveTicker(subscribe: SubscribeTickerEntity): Flowable<TickerData>

    fun subscribeAndObserveOrderBook(subscribe: SubscribeOrderBookEntity): Flowable<OrderBookData>
}
package gts.bitfinex.data.remote

import io.reactivex.Flowable

import gts.bitfinex.data.entity.SubscribeTickerRequest
import gts.bitfinex.data.entity.SubscribeOrderBookRequest

interface BitfinexClient {

    fun subscribeTicker(subscribeTickerRequest: SubscribeTickerRequest): Flowable<Array<String>>

    fun subscribeOrderBook(subscribeOrderBookRequest: SubscribeOrderBookRequest): Flowable<DoubleArray>
}

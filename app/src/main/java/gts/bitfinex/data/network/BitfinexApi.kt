package gts.bitfinex.data.network

import io.reactivex.Flowable

import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send

import gts.bitfinex.data.model.JsonResponse
import gts.bitfinex.data.model.TickerRequestBase
import gts.bitfinex.data.model.OrderBookRequestBase

interface BitfinexApi {

    @Receive
    fun openWebSocketEvent(): Flowable<WebSocket.Event>

    @Receive
    fun receiveResponse(): Flowable<JsonResponse>

    @Send
    fun sendTickerRequest(subscribeTicker: TickerRequestBase)

    @Receive
    fun observeTicker(): Flowable<Array<String>>

    @Send
    fun sendOrderBookRequest(subscribeOrderBook: OrderBookRequestBase)

    @Receive
    fun observeOrderBook(): Flowable<DoubleArray>

    companion object {
        const val BASE_URI = "wss://api.bitfinex.com/ws/"
    }
}
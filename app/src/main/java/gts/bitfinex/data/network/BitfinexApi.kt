package gts.bitfinex.data.network

import io.reactivex.Flowable

import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send

import gts.bitfinex.data.model.JsonResponse
import gts.bitfinex.data.model.TickerRequest
import gts.bitfinex.data.model.OrderBookRequest

interface BitfinexApi {

    @Receive
    fun openWebSocketEvent(): Flowable<WebSocket.Event>

    @Receive
    fun receiveResponse(): Flowable<JsonResponse>

    @Send
    fun sendTickerRequest(subscribeTicker: TickerRequest)

    @Receive
    fun observeTicker(): Flowable<Array<String>>

    @Send
    fun sendOrderBookRequest(subscribeOrderBook: OrderBookRequest)

    @Receive
    fun observeOrderBook(): Flowable<FloatArray>

    companion object {
        const val BASE_URI = "wss://api.bitfinex.com/ws/"
    }
}
package gts.bitfinex.data.remote

import io.reactivex.Flowable

import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send

import gts.bitfinex.data.entity.JsonResponse
import gts.bitfinex.data.entity.SubscribeTickerRequest
import gts.bitfinex.data.entity.SubscribeOrderBookRequest

interface BitfinexApi {

    @Receive
    fun openWebSocketEvent(): Flowable<WebSocket.Event>

    @Receive
    fun receiveResponse(): Flowable<JsonResponse>

    @Send
    fun sendTickerRequest(subscribeTickerRequest: SubscribeTickerRequest)

    @Receive
    fun observeTicker(): Flowable<Array<String>>

    @Send
    fun sendOrderBookRequest(subscribeOrderBookRequest: SubscribeOrderBookRequest)

    @Receive
    fun observeOrderBook(): Flowable<DoubleArray>

    companion object {
        const val BASE_URI = "wss://api.bitfinex.com/ws/"
    }
}
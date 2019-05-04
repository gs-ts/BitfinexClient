package gts.bitfinex.data

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

import io.reactivex.Flowable

import com.tinder.scarlet.WebSocket

import gts.bitfinex.data.network.BitfinexApi
import gts.bitfinex.data.network.BitfinexRepository
import gts.bitfinex.domain.entities.SubscribeOrderBookEntity
import gts.bitfinex.domain.entities.SubscribeTickerEntity

class BitfinexRepositoryTest {

    private lateinit var bitfinexApi: BitfinexApi
    private lateinit var bitfinexRepository: BitfinexRepository

    @Before
    fun before() {
        bitfinexApi = mock(BitfinexApi::class.java)
        bitfinexRepository = BitfinexRepository(bitfinexApi)
    }

    @Test
    fun `Given subscribe to ticker, When OnConnectionOpened WebSocket event, Then return expected data`() {
        val subscribeTicker = SubscribeTickerEntity(
            event = "event",
            pair = "pair",
            channel = "channel"
        )

        // response from service
        val tickerArray = arrayOf(
            "chanId",
            "bid",
            "bidSize",
            "ask",
            "askSize",
            "dailyChange",
            "dailyChangePerc",
            "lastPrice",
            "volume",
            "high",
            "low"
        )

        Mockito.`when`(bitfinexApi.openWebSocketEvent()).thenReturn(Flowable.just(WebSocket.Event.OnConnectionOpened<Any>("")))
        Mockito.`when`(bitfinexApi.observeTicker()).thenReturn(Flowable.just(tickerArray))

        bitfinexRepository.subscribeAndObserveTicker(subscribeTicker).test()
            .await()
            .assertValue { tickerData ->
                tickerData.chanId == tickerArray[0]
                        &&
                        tickerData.bid == tickerArray[1]
                        &&
                        tickerData.bidSize == tickerArray[2]
                        &&
                        tickerData.ask == tickerArray[3]
                        &&
                        tickerData.askSize == tickerArray[4]
                        &&
                        tickerData.dailyChange == tickerArray[5]
                        &&
                        tickerData.dailyChangePerc == tickerArray[6]
                        &&
                        tickerData.lastPrice == tickerArray[7]
                        &&
                        tickerData.volume == tickerArray[8]
                        &&
                        tickerData.high == tickerArray[9]
                        &&
                        tickerData.low == tickerArray[10]
            }
            .assertComplete()
    }

    @Test
    fun `Given subscribe to order book, When OnConnectionOpened WebSocket event, Then return expected data`() {
        val subscribeOrderBook = SubscribeOrderBookEntity(
            event = "event",
            pair = "pair",
            channel = "channel",
            frequency = "freq"
        )

        // response from service
        val orderBookArray = doubleArrayOf(
            5770.0, // channel
            5.93, // price
            1.0, // count
            1000.5 // amount
        )

        Mockito.`when`(bitfinexApi.openWebSocketEvent()).thenReturn(Flowable.just(WebSocket.Event.OnConnectionOpened<Any>("")))
        Mockito.`when`(bitfinexApi.observeOrderBook()).thenReturn(Flowable.just(orderBookArray))

        bitfinexRepository.subscribeAndObserveOrderBook(subscribeOrderBook).test()
            .await()
            .assertValue { orderBookData ->
                orderBookData.price == orderBookArray[1] &&
                        orderBookData.count == orderBookArray[2].toInt() &&
                        orderBookData.amount == orderBookArray[3]
            }
            .assertComplete()
    }
}
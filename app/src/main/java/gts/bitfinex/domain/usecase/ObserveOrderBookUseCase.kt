package gts.bitfinex.domain.usecase

import io.reactivex.Flowable

import gts.bitfinex.domain.BitfinexService
import gts.bitfinex.domain.model.OrderBookData
import gts.bitfinex.domain.entities.SubscribeEntity
import gts.bitfinex.domain.entities.SubscribeOrderBookEntity

class ObserveOrderBookUseCase(private val bitfinexService: BitfinexService) {

    operator fun invoke(): Flowable<OrderBookData> {
        val subscribeOrderBook = SubscribeOrderBookEntity(
            event = SubscribeEntity.SUBSCRIBE_EVENT,
            channel = SubscribeEntity.ORDER_BOOK_CHANNEL,
            pair = SubscribeEntity.BTCUSD_PAIR,
            frequency = SubscribeEntity.FREQUENCY_ZERO
        )
        return bitfinexService.subscribeAndObserveOrderBook(subscribeOrderBook)
    }
}
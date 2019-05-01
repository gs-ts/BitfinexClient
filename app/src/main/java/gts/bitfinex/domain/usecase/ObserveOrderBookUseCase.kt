package gts.bitfinex.domain.usecase

import io.reactivex.Flowable

import gts.bitfinex.domain.BitfinexService
import gts.bitfinex.domain.toOrderBookModel
import gts.bitfinex.domain.entities.SubscribeEntity
import gts.bitfinex.domain.entities.SubscribeOrderBookEntity
import gts.bitfinex.presentation.model.OrderBook

class ObserveOrderBookUseCase(private val bitfinexService: BitfinexService) {

    operator fun invoke(): Flowable<OrderBook> {
        val subscribeOrderBook = SubscribeOrderBookEntity(
            event = SubscribeEntity.SUBSCRIBE_EVENT,
            channel = SubscribeEntity.ORDER_BOOK_CHANNEL,
            pair = SubscribeEntity.BTCUSD_PAIR,
            frequency = SubscribeEntity.FREQUENCY_ZERO
        )
        return bitfinexService.subscribeAndObserveOrderBook(subscribeOrderBook)
            .map { orderBook -> orderBook.toOrderBookModel() }
    }
}
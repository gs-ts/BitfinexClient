package gts.bitfinex.domain.usecase

import io.reactivex.Flowable

import gts.bitfinex.domain.BitfinexRepository
import gts.bitfinex.domain.model.OrderBookData
import gts.bitfinex.domain.entities.BaseSubscribe
import gts.bitfinex.domain.entities.SubscribeOrderBook

class ObserveOrderBookUseCase(private val bitfinexRepository: BitfinexRepository) {

    operator fun invoke(): Flowable<OrderBookData> {
        val subscribeOrderBook = SubscribeOrderBook(
            event = BaseSubscribe.SUBSCRIBE_EVENT,
            channel = BaseSubscribe.ORDER_BOOK_CHANNEL,
            pair = BaseSubscribe.BTCUSD_PAIR,
            frequency = BaseSubscribe.FREQUENCY_ZERO
        )
        return bitfinexRepository.ObserveOrderBook(subscribeOrderBook)
    }
}
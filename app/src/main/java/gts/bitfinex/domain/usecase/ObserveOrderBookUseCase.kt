package gts.bitfinex.domain.usecase

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

import gts.bitfinex.domain.BitfinexService
import gts.bitfinex.domain.model.OrderBookData
import gts.bitfinex.domain.entities.SubscribeEntity
import gts.bitfinex.domain.entities.SubscribeOrderBook

class ObserveOrderBookUseCase(private val bitfinexService: BitfinexService) {

    operator fun invoke(): Flowable<OrderBookData> {
        val subscribeOrderBook = SubscribeOrderBook(
            event = SubscribeEntity.SUBSCRIBE_EVENT,
            channel = SubscribeEntity.ORDER_BOOK_CHANNEL,
            pair = SubscribeEntity.BTCUSD_PAIR,
            frequency = SubscribeEntity.FREQUENCY_ZERO
        )
        return bitfinexService.subscribeAndObserveOrderBook(subscribeOrderBook)
            .observeOn(Schedulers.computation())
            .subscribeOn(AndroidSchedulers.mainThread())
    }
}
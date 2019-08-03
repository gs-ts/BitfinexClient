package gts.bitfinex.domain.usecase

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

import gts.bitfinex.domain.BitfinexService
import gts.bitfinex.domain.model.TickerData
import gts.bitfinex.domain.entities.SubscribeEntity
import gts.bitfinex.domain.entities.SubscribeTicker

class ObserveTickerUseCase(private val bitfinexService: BitfinexService) {

    operator fun invoke(): Flowable<TickerData> {
        val subscribeTicker = SubscribeTicker(
            event = SubscribeEntity.SUBSCRIBE_EVENT,
            channel = SubscribeEntity.TICKER_CHANNEL,
            pair = SubscribeEntity.BTCUSD_PAIR
        )
        return bitfinexService.subscribeAndObserveTicker(subscribeTicker)
            .observeOn(Schedulers.computation())
            .subscribeOn(AndroidSchedulers.mainThread())
    }
}
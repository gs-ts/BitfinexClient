package gts.bitfinex.domain.usecase

import io.reactivex.Flowable

import gts.bitfinex.domain.BitfinexService
import gts.bitfinex.domain.model.TickerData
import gts.bitfinex.domain.entities.SubscribeEntity
import gts.bitfinex.domain.entities.SubscribeTickerEntity

class ObserveTickerUseCase(private val bitfinexService: BitfinexService) {

    operator fun invoke(): Flowable<TickerData> {
        val subscribeTicker = SubscribeTickerEntity(
            event = SubscribeEntity.SUBSCRIBE_EVENT,
            channel = SubscribeEntity.TICKER_CHANNEL,
            pair = SubscribeEntity.BTCUSD_PAIR
        )
        return bitfinexService.subscribeAndObserveTicker(subscribeTicker)
    }
}
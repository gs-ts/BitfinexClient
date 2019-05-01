package gts.bitfinex.domain.usecase

import io.reactivex.Flowable

import gts.bitfinex.domain.BitfinexService
import gts.bitfinex.domain.toTickerModel
import gts.bitfinex.domain.entities.SubscribeEntity
import gts.bitfinex.domain.entities.SubscribeTickerEntity
import gts.bitfinex.presentation.model.Ticker

class ObserveTickerUseCase(private val bitfinexService: BitfinexService) {

    operator fun invoke(): Flowable<Ticker> {
        val subscribeTicker = SubscribeTickerEntity(
            event = SubscribeEntity.SUBSCRIBE_EVENT,
            channel = SubscribeEntity.TICKER_CHANNEL,
            pair = SubscribeEntity.BTCUSD_PAIR
        )
        return bitfinexService.subscribeAndObserveTicker(subscribeTicker)
            .map { tickerData -> tickerData.toTickerModel() }
    }
}
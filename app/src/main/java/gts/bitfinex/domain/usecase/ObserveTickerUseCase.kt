package gts.bitfinex.domain.usecase

import io.reactivex.Flowable

import gts.bitfinex.domain.BitfinexRepository
import gts.bitfinex.domain.model.TickerData
import gts.bitfinex.domain.entities.BaseSubscribe
import gts.bitfinex.domain.entities.SubscribeTicker

class ObserveTickerUseCase(private val bitfinexRepository: BitfinexRepository) {

    operator fun invoke(): Flowable<TickerData> {
        val subscribeTicker = SubscribeTicker(
            event = BaseSubscribe.SUBSCRIBE_EVENT,
            channel = BaseSubscribe.TICKER_CHANNEL,
            pair = BaseSubscribe.BTCUSD_PAIR
        )
        return bitfinexRepository.ObserveTicker(subscribeTicker)
    }
}
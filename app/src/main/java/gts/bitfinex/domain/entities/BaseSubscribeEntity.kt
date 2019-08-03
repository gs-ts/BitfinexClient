package gts.bitfinex.domain.entities

abstract class BaseSubscribeEntity(
    open val event: String,
    open val channel: String,
    open val pair: String
) {
    companion object {
        const val SUBSCRIBE_EVENT = "subscribe"
        const val UNSUBSCRIBE_EVENT = "unsubscribe"
        const val TICKER_CHANNEL = "ticker"
        const val ORDER_BOOK_CHANNEL = "book"
        const val BTCUSD_PAIR = "BTCUSD"
        const val FREQUENCY_ZERO = "F0"
        const val FREQUENCY_ONE = "F1"
    }
}
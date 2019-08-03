package gts.bitfinex.domain.entities

class SubscribeOrderBook(
    override val event: String,
    override val channel: String,
    override val pair: String,
    val frequency: String
) : BaseSubscribeEntity(event, channel, pair)
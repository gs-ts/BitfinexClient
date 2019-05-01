package gts.bitfinex.domain.entities

class SubscribeOrderBookEntity(
    override val event: String,
    override val channel: String,
    override val pair: String,
    val frequency: String
) : SubscribeEntity(event, channel, pair)
package gts.bitfinex.domain.entities

class SubscribeTicker(
    override val event: String,
    override val channel: String,
    override val pair: String
) : BaseSubscribeEntity(event, channel, pair)
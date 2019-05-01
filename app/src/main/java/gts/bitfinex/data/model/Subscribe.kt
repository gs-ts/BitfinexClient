package gts.bitfinex.data.model

import com.squareup.moshi.Json

import gts.bitfinex.domain.entities.SubscribeOrderBookEntity
import gts.bitfinex.domain.entities.SubscribeTickerEntity

abstract class SubscribeRequest(
    @Json(name = "event")
    open val event: String,
    @Json(name = "channel")
    open val channel: String,
    @Json(name = "pair")
    open val pair: String
)

class OrderBookRequest(
    override val event: String,
    override val channel: String,
    override val pair: String,
    @Json(name = "freq")
    val frequency: String
) : SubscribeRequest(event, channel, pair)

fun SubscribeOrderBookEntity.toSubcribeOrderBookrRequest() = OrderBookRequest(
    event = event,
    channel = channel,
    pair = pair,
    frequency = frequency
)

data class TickerRequest(
    override val event: String,
    override val channel: String,
    override val pair: String
) : SubscribeRequest(event, channel, pair)

fun SubscribeTickerEntity.toSubcribeTickerRequest() = TickerRequest(
    event = event,
    channel = channel,
    pair = pair
)
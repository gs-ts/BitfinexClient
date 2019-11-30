package gts.bitfinex.data.entity

import com.squareup.moshi.Json

import gts.bitfinex.domain.entities.SubscribeTicker
import gts.bitfinex.domain.entities.SubscribeOrderBook

abstract class BaseSubscribeRequest(
    @Json(name = "event")
    open val event: String,
    @Json(name = "channel")
    open val channel: String,
    @Json(name = "pair")
    open val pair: String
)

data class SubscribeTickerRequest(
    override val event: String,
    override val channel: String,
    override val pair: String
) : BaseSubscribeRequest(event, channel, pair)

fun SubscribeTicker.toSubcribeTickerRequest() =
    SubscribeTickerRequest(
        event = event,
        channel = channel,
        pair = pair
    )

class SubscribeOrderBookRequest(
    override val event: String,
    override val channel: String,
    override val pair: String,
    @Json(name = "freq")
    val frequency: String
) : BaseSubscribeRequest(event, channel, pair)

fun SubscribeOrderBook.toSubcribeOrderBookrRequest() =
    SubscribeOrderBookRequest(
        event = event,
        channel = channel,
        pair = pair,
        frequency = frequency
    )
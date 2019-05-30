package gts.bitfinex.presentation.model

import gts.bitfinex.domain.model.TickerData

data class Ticker(
    var dailyChange: String,
    var lastPrice: String,
    var volume: String,
    var high: String,
    var low: String
)

fun TickerData.toTickerModel() = Ticker(
    dailyChange = dailyChange,
    lastPrice = lastPrice,
    volume = volume,
    high = high,
    low = low
)
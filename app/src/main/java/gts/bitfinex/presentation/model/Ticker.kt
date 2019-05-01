package gts.bitfinex.presentation.model

data class Ticker(
    var dailyChange: String,
    var lastPrice: String,
    var volume: String,
    var high: String,
    var low: String
)
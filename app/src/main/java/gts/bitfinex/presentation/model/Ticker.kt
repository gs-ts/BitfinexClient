package gts.bitfinex.presentation.model

data class Ticker(
    var lastPrice: String,
    var volume: String,
    var low: String,
    var high: String,
    var change: String
)
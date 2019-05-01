package gts.bitfinex.domain

import gts.bitfinex.domain.model.TickerData
import gts.bitfinex.domain.model.OrderBookData
import gts.bitfinex.presentation.model.Ticker
import gts.bitfinex.presentation.model.OrderBook

fun TickerData.toTickerModel() = Ticker(
    lastPrice = lastPrice,
    volume = volume,
    low = low,
    high = high,
    change = dailyChange
)

fun OrderBookData.toOrderBookModel() = OrderBook(
    amount = amount.toString(),
    count =count.toString(),
    price = price.toString()
)
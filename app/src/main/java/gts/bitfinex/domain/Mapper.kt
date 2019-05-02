package gts.bitfinex.domain

import gts.bitfinex.domain.model.TickerData
import gts.bitfinex.domain.model.OrderBookData
import gts.bitfinex.presentation.model.Ticker
import gts.bitfinex.presentation.model.OrderBook

fun TickerData.toTickerModel() = Ticker(
    dailyChange = dailyChange,
    lastPrice = lastPrice,
    volume = volume,
    high = high,
    low = low
)

fun OrderBookData.toOrderBookModel() = OrderBook(
    price = price,
    count = count,
    amount = amount
)
package gts.bitfinex.presentation.model

import gts.bitfinex.domain.model.OrderBookData

data class OrderBook(
    val amount: Double,
    val count: Int,
    val price: Double
)

fun OrderBookData.toOrderBookModel() = OrderBook(
    price = price,
    count = count,
    amount = amount
)
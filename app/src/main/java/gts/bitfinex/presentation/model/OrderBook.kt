package gts.bitfinex.presentation.model

data class OrderBook(
    val amount: Double,
    val count: Int,
    val price: Double
)
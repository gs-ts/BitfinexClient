package gts.bitfinex.domain.model

data class OrderBookData(
    val price: Double,
    val count: Int,
    val amount: Double
)

fun DoubleArray.toOrderBookData() = OrderBookData(
    price = this[1],
    count = this[2].toInt(),
    amount = this[3]
)

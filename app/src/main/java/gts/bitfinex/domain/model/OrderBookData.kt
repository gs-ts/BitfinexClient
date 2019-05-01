package gts.bitfinex.domain.model

data class OrderBookData(
    val price: Float,
    val count: Int,
    val amount: Float
)

fun FloatArray.toOrderBookData() = OrderBookData(
    price = this[1],
    count = this[2].toInt(),
    amount = this[3]
)
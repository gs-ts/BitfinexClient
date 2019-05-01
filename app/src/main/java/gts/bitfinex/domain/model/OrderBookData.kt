package gts.bitfinex.domain.model

data class OrderBookData(
    val amount: Float,
    val count: Int,
    val price: Float
)

fun FloatArray.toOrderBookData() = OrderBookData(
    amount = this[3],
    count = this[2].toInt(),
    price = this[1]
)
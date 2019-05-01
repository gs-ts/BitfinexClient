package gts.bitfinex.domain.model

data class TickerData(
    val chanId: String,
    val bid: String,
    val bidSize: String,
    val ask: String,
    val askSize: String,
    val dailyChange: String,
    val dailyChangePerc: String,
    val lastPrice: String,
    val volume: String,
    val high: String,
    val low: String
)

fun Array<String>.toTickerData() = TickerData(
    chanId =            this[0],
    bid =               this[1],
    bidSize =           this[2],
    ask =               this[3],
    askSize =           this[4],
    dailyChange =       this[5],
    dailyChangePerc =   this[6],
    lastPrice =         this[7],
    volume =            this[8],
    high =              this[9],
    low =               this[10]
)

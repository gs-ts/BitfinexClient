package gts.bitfinex.presentation.ui

import java.util.TreeMap
import kotlin.collections.ArrayList
import gts.bitfinex.presentation.model.OrderBook

private const val LIST_SIZE = 20
private val orderBooksByPrice: TreeMap<Double, OrderBook> = TreeMap()

fun OrderBook.buildOrderBooks(): List<OrderBook> {
    if (count != 0)
        orderBooksByPrice[price] = this //pair(amount, count)
    else
        orderBooksByPrice.remove(price)

    val orderBookList: ArrayList<OrderBook> = arrayListOf()
    orderBooksByPrice.forEach { (_, orderBook) ->
        orderBookList.add(orderBook)
    }

    return orderBookList
}

fun getOrderBookBidList(list: List<OrderBook>): List<OrderBook> {
    return list.filter { book -> book.amount > 0 }.reversed().take(LIST_SIZE)
}

fun getOrderBookAskList(list: List<OrderBook>): List<OrderBook> {
    return list.filter { book -> book.amount < 0 }.take(LIST_SIZE)
}
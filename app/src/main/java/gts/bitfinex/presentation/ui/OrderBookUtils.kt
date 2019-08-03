package gts.bitfinex.presentation.ui

import android.util.Pair
import java.util.TreeMap
import kotlin.collections.ArrayList
import gts.bitfinex.presentation.model.OrderBook

private const val LIST_SIZE = 20
private val orderBooks: TreeMap<Double, Pair<Double, Int>> = TreeMap()

fun OrderBook.buildOrderBooks(): List<Triple<Double, Double, Int>> {
    if (count != 0)
        orderBooks[price] = Pair(amount, count)
    else
        orderBooks.remove(price)

    val orderBookList: ArrayList<Triple<Double, Double, Int>> = ArrayList()
    orderBooks.forEach { (key, value) ->
        orderBookList.add(Triple(key, value.first, value.second))
    }

    return orderBookList
}

fun getOrderBookBidList(list: List<Triple<Double, Double, Int>>): List<Triple<Double, Double, Int>> {
    return list.filter { t -> t.second > 0 }.reversed().take(LIST_SIZE)
}

fun getOrderBookAskList(list: List<Triple<Double, Double, Int>>): List<Triple<Double, Double, Int>> {
    return list.filter { t -> t.second < 0 }.take(LIST_SIZE)
}
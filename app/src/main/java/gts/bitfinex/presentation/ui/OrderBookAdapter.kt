package gts.bitfinex.presentation.ui

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

import kotlin.math.absoluteValue

import kotlinx.android.synthetic.main.order_book_bid_item.view.amount
import kotlinx.android.synthetic.main.order_book_bid_item.view.price

import gts.bitfinex.R
import gts.bitfinex.presentation.model.OrderBook

class OrderBookAdapter(private val isBidItem: Boolean) : RecyclerView.Adapter<OrderBookHolder>() {

    private val orders: ArrayList<OrderBook> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderBookHolder {
        return if (isBidItem) {
            OrderBookHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.order_book_bid_item,
                    parent,
                    false
                )
            )
        } else {
            OrderBookHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.order_book_ask_item,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount() = orders.size

    override fun onBindViewHolder(holder: OrderBookHolder, position: Int) {
        holder.bindItems(orders[position])
    }

    fun addOrderBooks(orderBooks: List<OrderBook>) {
        orders.clear()
        orders.addAll(orderBooks)
        notifyDataSetChanged()
    }
}

class OrderBookHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bindItems(order: OrderBook) = with(view) {
        amount.text = order.amount.absoluteValue.toString()
        price.text = order.price.toString()
    }
}

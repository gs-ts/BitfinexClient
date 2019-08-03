package gts.bitfinex.presentation.ui

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

import kotlin.math.absoluteValue

import kotlinx.android.synthetic.main.order_book_bid_item.view.amount
import kotlinx.android.synthetic.main.order_book_bid_item.view.price

import gts.bitfinex.R

class OrderBookAdapter(private val isBidItem: Boolean) : RecyclerView.Adapter<OrderBookHolder>() {

    private val orders: ArrayList<Triple<Double, Double, Int>> = ArrayList()

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

    fun addOrderBooks(orderBooks: List<Triple<Double, Double, Int>>) {
        orders.clear()
        orders.addAll(orderBooks)
        notifyDataSetChanged()
    }
}

class OrderBookHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bindItems(order: Triple<Double, Double, Int>) = with(view) {
        amount.text = order.second.absoluteValue.toString()
        price.text = order.first.toString()
    }
}

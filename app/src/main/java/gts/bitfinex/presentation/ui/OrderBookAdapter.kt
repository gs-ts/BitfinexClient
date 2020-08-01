package gts.bitfinex.presentation.ui

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

import kotlin.math.absoluteValue

import gts.bitfinex.R
import gts.bitfinex.presentation.model.OrderBook
import gts.bitfinex.databinding.OrderBookBidItemBinding

private const val VIEW_TYPE_BID = 0
private const val VIEW_TYPE_ASK = 1

class OrderBookAdapter : RecyclerView.Adapter<OrderBookHolder>() {

    private val orders: ArrayList<OrderBook> = ArrayList()

    override fun getItemViewType(position: Int): Int {
        return if (orders[position].amount > 0) {
            VIEW_TYPE_BID
        } else {
            VIEW_TYPE_ASK
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderBookHolder {
        if (viewType == VIEW_TYPE_BID) {
            return OrderBookHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.order_book_bid_item,
                    parent,
                    false
                )
            )
        } else if (viewType == VIEW_TYPE_ASK) {
            return OrderBookHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.order_book_ask_item,
                    parent,
                    false
                )
            )
        }
        return super.createViewHolder(parent, viewType)
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
        OrderBookBidItemBinding.bind(view).amount.text = order.amount.absoluteValue.toString()
        OrderBookBidItemBinding.bind(view).price.text = order.price.toString()
    }
}

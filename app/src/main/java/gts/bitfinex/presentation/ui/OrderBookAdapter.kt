package gts.bitfinex.presentation.ui

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gts.bitfinex.R

import kotlin.math.absoluteValue

import gts.bitfinex.presentation.model.OrderBook
import gts.bitfinex.databinding.OrderBookBidItemBinding

class OrderBookAdapter : ListAdapter<OrderBook, OrderBookAdapter.OrderBookViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<OrderBook>() {

        override fun areItemsTheSame(oldItem: OrderBook, newItem: OrderBook) = when {
            newItem.amount > 0 -> oldItem == newItem // bid type
            newItem.amount < 0 -> oldItem == newItem // ask type
            else -> oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: OrderBook, newItem: OrderBook) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        OrderBookViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).amount > 0) {
            R.layout.order_book_bid_item
        } else {
            R.layout.order_book_ask_item
        }
    }

    override fun onBindViewHolder(viewHolder: OrderBookViewHolder, position: Int) {
        viewHolder.bindOrderBookItems(getItem(position))
    }

    class OrderBookViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindOrderBookItems(order: OrderBook) = with(OrderBookBidItemBinding.bind(view)) {
            amount.text = order.amount.absoluteValue.toString()
            price.text = order.price.toString()
        }
    }
}

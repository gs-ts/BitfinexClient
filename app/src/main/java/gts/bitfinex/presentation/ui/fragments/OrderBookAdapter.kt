package gts.bitfinex.presentation.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gts.bitfinex.R
import gts.bitfinex.presentation.model.OrderBook
import kotlinx.android.synthetic.main.order_book_item.view.*

class OrderBookAdapter : RecyclerView.Adapter<OrderBookHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderBookHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.order_book_item,
            parent,
            false)
        return OrderBookHolder(view)

//        return LayoutInflater.from(parent.context).run {
//            OrderBookItemBinding.inflate(this, parent, false)
//        }.run { OrderBookHolder(this) }
    }

    private var orderBooks: OrderBook? = null

    override fun getItemCount(): Int {
        return size
//        return 25
//        return orderBooks.size
    }

    override fun onBindViewHolder(holder: OrderBookHolder, position: Int) {
//        val orderBook = orderBooks[position]
        holder.bind(orderBooks)
    }

    var size: Int = 0
    fun addOrderBooks(orderBooks: OrderBook) {
        this.orderBooks = orderBooks
        size++
        if (size == 25) size = 0
        notifyItemChanged(size)
//        notifyDataSetChanged()
    }

//    class OrderBookHolder(private val binding: OrderBookItemBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(orderBook: OrderBook) = with(binding) {
//            this.orderBook = orderBook
//            executePendingBindings()
//        }
//    }
}

class OrderBookHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(orderBook: OrderBook?) = with(itemView) {
        amount.text = orderBook?.amount
        count.text = orderBook?.count
        price.text = orderBook?.price
    }
}
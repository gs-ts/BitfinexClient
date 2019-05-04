package gts.bitfinex.presentation.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gts.bitfinex.R
import kotlinx.android.synthetic.main.order_book_bid_item.view.*
import java.util.*
import kotlin.math.absoluteValue

//TODO: https://stackoverflow.com/questions/29141729/recyclerview-no-adapter-attached-skipping-layout
class OrderBookAdapter : RecyclerView.Adapter<OrderBookHolder>() {

    //TODO: https://stackoverflow.com/questions/26245139/how-to-create-recyclerview-with-multiple-view-type
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderBookHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.order_book_bid_item,
            parent,
            false
        )
        return OrderBookHolder(view)
    }

    val orders: ArrayList<Triple<Double, Double, Int>> = ArrayList()

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrderBookHolder, position: Int) {
        holder.bindItems(orders[position])
//        val book = queue.elementAt(position)
//        holder.bind(book)
    }

    fun addOrderBooks(orderBooks: List<Triple<Double, Double, Int>>) {
        orders.clear()
        orders.addAll(orderBooks)
        notifyDataSetChanged()
    }
}

class OrderBookHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bindItems(order: Triple<Double, Double, Int>) = with(view) {
        amount.text = order.second.absoluteValue.toString()
        price.text = order.first.toString()
    }
}

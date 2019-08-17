package gts.bitfinex.presentation.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.bitfinex_fragment.order_book_bid_list
import kotlinx.android.synthetic.main.bitfinex_fragment.order_book_ask_list

import org.koin.androidx.viewmodel.ext.android.viewModel

import gts.bitfinex.databinding.BitfinexFragmentBinding

class BitfinexFragment : Fragment() {

    private val viewModel: BitfinexViewModel by viewModel()
    private lateinit var binding: BitfinexFragmentBinding
    private lateinit var orderBookBidAdapter: OrderBookAdapter
    private lateinit var orderBookAskAdapter: OrderBookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BitfinexFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderBookBidAdapter = OrderBookAdapter()
        order_book_bid_list.layoutManager = LinearLayoutManager(activity)
        order_book_bid_list.adapter = orderBookBidAdapter

        orderBookAskAdapter = OrderBookAdapter()
        order_book_ask_list.layoutManager = LinearLayoutManager(activity)
        order_book_ask_list.adapter = orderBookAskAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.ticker.observe(this, Observer { ticker ->
            binding.ticker = ticker
        })

        viewModel.orderBooks.observe(this, Observer { orderBookList ->
            orderBookBidAdapter.addOrderBooks(getOrderBookBidList(orderBookList))
            orderBookAskAdapter.addOrderBooks(getOrderBookAskList(orderBookList))
        })
    }

    companion object {
        fun newInstance() = BitfinexFragment()
    }
}

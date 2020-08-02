package gts.bitfinex.presentation.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment

import org.koin.androidx.viewmodel.ext.android.viewModel

import gts.bitfinex.databinding.BitfinexFragmentBinding

class BitfinexFragment : Fragment() {

    private val viewModel: BitfinexViewModel by viewModel()
    private var _binding: BitfinexFragmentBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    private lateinit var orderBookBidAdapter: OrderBookAdapter
    private lateinit var orderBookAskAdapter: OrderBookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BitfinexFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderBookBidAdapter = OrderBookAdapter()
        binding.orderBookBidList.adapter = orderBookBidAdapter

        orderBookAskAdapter = OrderBookAdapter()
        binding.orderBookAskList.adapter = orderBookAskAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.ticker.observe(viewLifecycleOwner, Observer { ticker ->
            binding.ticker = ticker
        })

        viewModel.orderBooks.observe(viewLifecycleOwner, Observer { orderBookList ->
            orderBookBidAdapter.submitList(getOrderBookBidList(orderBookList))
            orderBookAskAdapter.submitList(getOrderBookAskList(orderBookList))
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = BitfinexFragment()
    }
}

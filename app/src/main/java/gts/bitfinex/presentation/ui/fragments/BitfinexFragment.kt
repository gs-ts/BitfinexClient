package gts.bitfinex.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import gts.bitfinex.databinding.BitfinexFragmentBinding
import kotlinx.android.synthetic.main.bitfinex_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BitfinexFragment : Fragment() {

    private val viewModel: BitfinexViewModel by sharedViewModel()
    lateinit var binding: BitfinexFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var orderBookAdapter: OrderBookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BitfinexFragmentBinding.inflate(inflater, container, false)
        return binding.root
//        return inflater.inflate(R.layout.bitfinex_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel

        viewModel.ticker.observe(this, Observer {
            binding.ticker = it
        })

        viewModel.orderBook.observe(this, Observer {
            orderBookAdapter.addOrderBooks(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderBookAdapter = OrderBookAdapter()
        recyclerView = order_book_list
        recyclerView.adapter = orderBookAdapter
    }

    companion object {
        fun newInstance() = BitfinexFragment()
    }
}

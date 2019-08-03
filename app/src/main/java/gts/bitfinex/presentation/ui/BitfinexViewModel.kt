package gts.bitfinex.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

import java.util.ArrayList

import io.reactivex.disposables.CompositeDisposable

import gts.bitfinex.presentation.model.Ticker
import gts.bitfinex.presentation.model.OrderBook
import gts.bitfinex.presentation.model.toTickerModel
import gts.bitfinex.presentation.model.toOrderBookModel
import gts.bitfinex.domain.usecase.ObserveTickerUseCase
import gts.bitfinex.domain.usecase.ObserveOrderBookUseCase

import timber.log.Timber

class BitfinexViewModel(
    val observeTickerUseCase: ObserveTickerUseCase,
    val observeOrderBookUseCase: ObserveOrderBookUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _ticker = MutableLiveData<Ticker>()
    val ticker: LiveData<Ticker>
        get() = _ticker

    private val _orderBooks = MutableLiveData<List<OrderBook>>()
    val orderBooks: LiveData<List<OrderBook>>
        get() = _orderBooks

    init {
        compositeDisposable.add(observeTickerUseCase.invoke()
            .map { tickerData -> tickerData.toTickerModel() }
            .subscribe({ ticker ->
                _ticker.postValue(ticker)
            }, { e ->
                Timber.e("observeTickerUseCase error: %s", e.toString())
            })
        )

        compositeDisposable.add(observeOrderBookUseCase.invoke()
            .map { orderBookData -> orderBookData.toOrderBookModel() }
            .map { orderBook ->
                orderBook.buildOrderBooks()
            }
            .subscribe({ orderBookList ->
                _orderBooks.postValue(ArrayList(orderBookList))
            }, { e ->
                Timber.e("observeOrderBookUseCase error: %s", e.toString())
            })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}

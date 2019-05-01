package gts.bitfinex.presentation.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.android.schedulers.AndroidSchedulers

import gts.bitfinex.presentation.model.Ticker
import gts.bitfinex.presentation.model.OrderBook
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

    private val _orderBook = MutableLiveData<OrderBook>()
    val orderBook: LiveData<OrderBook>
        get() = _orderBook

    init {
        compositeDisposable.add(observeTickerUseCase.invoke()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ ticker ->
                _ticker.postValue(ticker)
            }, { e ->
                Timber.e("subscribeToTickerUseCase error: %s", e.toString())
            }))

        compositeDisposable.add(observeOrderBookUseCase.invoke()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ orderBook ->
//                Timber.d("orderBook = ${orderBook.amount}")
                _orderBook.postValue(orderBook)
            }, { e ->
                Timber.e("openWebSocketUseCase error: %s", e.toString())
            }))
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}

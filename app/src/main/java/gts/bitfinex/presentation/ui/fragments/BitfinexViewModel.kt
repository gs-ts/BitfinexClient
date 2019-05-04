package gts.bitfinex.presentation.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

import java.util.ArrayList

import io.reactivex.schedulers.Schedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.android.schedulers.AndroidSchedulers

import gts.bitfinex.domain.usecase.ObserveTickerUseCase
import gts.bitfinex.domain.usecase.ObserveOrderBookUseCase
import gts.bitfinex.presentation.model.Ticker
import gts.bitfinex.presentation.ui.buildOrderBooks

import timber.log.Timber

class BitfinexViewModel(
    val observeTickerUseCase: ObserveTickerUseCase,
    val observeOrderBookUseCase: ObserveOrderBookUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _ticker = MutableLiveData<Ticker>()
    val ticker: LiveData<Ticker>
        get() = _ticker

    private val _orderBooks = MutableLiveData<ArrayList<Triple<Double, Double, Int>>>()
    val orderBooks: LiveData<ArrayList<Triple<Double, Double, Int>>>
        get() = _orderBooks

    init {
        compositeDisposable.add(observeTickerUseCase.invoke()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ ticker ->
                _ticker.postValue(ticker)
            }, { e ->
                Timber.e("observeTickerUseCase error: %s", e.toString())
            })
        )

        compositeDisposable.add(observeOrderBookUseCase.invoke()
            .subscribeOn(Schedulers.computation())
            .map { orderBook ->
                orderBook.buildOrderBooks()
            }
            .observeOn(AndroidSchedulers.mainThread())
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

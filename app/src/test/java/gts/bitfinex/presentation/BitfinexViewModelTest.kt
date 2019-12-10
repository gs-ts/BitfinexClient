package gts.bitfinex.presentation

import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.Assert.assertEquals

import io.reactivex.Flowable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever

import gts.bitfinex.RxImmediateSchedulerRule
import gts.bitfinex.domain.model.TickerData
import gts.bitfinex.domain.model.OrderBookData
import gts.bitfinex.domain.usecase.ObserveOrderBookUseCase
import gts.bitfinex.domain.usecase.ObserveTickerUseCase
import gts.bitfinex.presentation.ui.BitfinexViewModel
import gts.bitfinex.presentation.model.toTickerModel
import gts.bitfinex.presentation.model.toOrderBookModel

class BitfinexViewModelTest {

    private lateinit var bitfinexViewModel: BitfinexViewModel
    private val mockObserveTickerUseCase: ObserveTickerUseCase = mock()
    private val mockObserveOrderBookUseCase: ObserveOrderBookUseCase = mock()

    private val tickerData =
        TickerData(
            "01",
            "1",
            "101",
            "2",
            "201",
            "3",
            "4",
            "5",
            "1000",
            "500",
            "50"
            )

    private val orderBookData1 = OrderBookData(50.5, 5, 100.5)
    private val orderBookData2 = OrderBookData(100.5, 10, 50.5)

    @Rule
    @JvmField
    val rxSchedulersOverrideRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `get ticker and orderbook data succeeds`() {
        // given
        whenever(mockObserveTickerUseCase.invoke()).thenReturn(Flowable.just(tickerData))
        whenever(mockObserveOrderBookUseCase.invoke()).thenReturn(Flowable.just(orderBookData1, orderBookData2))
        // when
        bitfinexViewModel = BitfinexViewModel(mockObserveTickerUseCase, mockObserveOrderBookUseCase)
        // then
        verify(mockObserveTickerUseCase).invoke()
        verify(mockObserveOrderBookUseCase).invoke()
        assertEquals(bitfinexViewModel.ticker.value, tickerData.toTickerModel())
        assertEquals(bitfinexViewModel.orderBooks.value, listOf(orderBookData1, orderBookData2).map { orderBookData -> orderBookData.toOrderBookModel() })
    }
}
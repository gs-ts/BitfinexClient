package gts.bitfinex.data

import gts.bitfinex.domain.model.TickerData
import io.reactivex.Observable
import org.junit.Test
import org.mockito.Mockito

class BitfinexRepositoryTest {

    @Test
    fun `When ticker data requested then return expected data`() {
        val tickerData = TickerData(
            chanId = 0,
            lastPrice = 1.toFloat(),
            volume = 1.toFloat(),
            low = 1.toFloat(),
            high = 1.toFloat(),
            change = 1.toFloat()
        )

    }
}
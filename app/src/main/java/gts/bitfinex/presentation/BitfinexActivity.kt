package gts.bitfinex.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import gts.bitfinex.R
import gts.bitfinex.presentation.ui.fragments.BitfinexFragment

class BitfinexActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bitfinex_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BitfinexFragment.newInstance())
                .commitNow()
        }
    }

}

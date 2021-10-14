package co.test.sphtestapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import co.test.sphtestapp.common.Constants.IntentKeys.Companion.YEAR_SELECTED

class YearTrackerReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val yearSelected = intent?.getStringExtra(YEAR_SELECTED)
        println("Year selected : $yearSelected")
    }
}
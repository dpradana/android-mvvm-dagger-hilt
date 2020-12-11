package daniel.base.application.core.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import daniel.base.application.core.utils.NetworkHelper

abstract class BaseActivity: AppCompatActivity() {
    //  bool
    var isInternetUnAvailableBool = false
    private lateinit var networkHelper: NetworkHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        setUpVariable()
        networkHelper = NetworkHelper(this)
    }

    abstract fun setLayout(): Int
    abstract fun setUpVariable()

    //  internet callbacks
    abstract fun internetAvailable()

    abstract fun internetUnAvailable()

    //  internet listener
    private var internetListener: BroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            if (networkHelper.isNetworkConnected() && isInternetUnAvailableBool) {
                internetAvailable()
                isInternetUnAvailableBool = false
            } else {
                if (!networkHelper.isNetworkConnected()) {
                    internetUnAvailable()
                    isInternetUnAvailableBool = true
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(internetListener, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(internetListener)
    }

}

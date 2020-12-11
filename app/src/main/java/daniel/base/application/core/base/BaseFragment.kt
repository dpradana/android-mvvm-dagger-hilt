package daniel.base.application.core.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import daniel.base.application.core.utils.NetworkHelper

abstract class BaseFragment : Fragment() {
    //  bool
    var isInternetUnAvailableBool = false
    private lateinit var networkHelper: NetworkHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(setLayout(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpVariable()
        context?.let {
            networkHelper = NetworkHelper(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
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
        context?.registerReceiver(internetListener, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onPause() {
        super.onPause()
        context?.unregisterReceiver(internetListener)
    }
}

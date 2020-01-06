package com.rongyi.mylibrary

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log

class NetworkCallbackImpl(private var manager: NetworkManager) :
    ConnectivityManager.NetworkCallback() {


    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)

        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.d("netState", "onCapabilitiesChanged: 网络类型为wifi")
                manager.post(NetType.WIFI)
            } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.d("netState", "onCapabilitiesChanged: 蜂窝网络")
                manager.post(NetType.MOBILE)
            } else {
                Log.d("netState", "onCapabilitiesChanged: 其他网络")
                manager.post(NetType.AUTO)
            }
        }
    }

//    override fun onLost() {
//        super.onLost()
//        Log.d("netState", "onAvailable: 网络已断开")
//        manager.post(NetType.NONE)
//    }

    override fun onLost(network: Network) {
        super.onLost(network)
        Log.d("netState", "onAvailable: 网络已断开")
        manager.post(NetType.NONE)
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        Log.d("netState", "onAvailable: 网络已连接")
    }
}
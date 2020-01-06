package com.rongyi.mylibrary

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.util.Log


class NetworkManager private constructor() {

    var map = HashMap<Any, ArrayList<MethodManager>>()

    companion object {
        val instance: NetworkManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkManager()
        }
    }


    @SuppressLint("MissingPermission")
    fun initialization(context: Context) {
        var networkCallback = NetworkCallbackImpl(this)
        val request = NetworkRequest.Builder().build()
        val connMgr =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connMgr.registerNetworkCallback(request, networkCallback)
    }

    fun post(netType: NetType) {

        for ((key, value) in map) {
            executeInvoke(key, value, netType)
        }

    }

    private fun executeInvoke(
        key: Any,
        value: ArrayList<MethodManager>,
        netType: NetType
    ) {

        value.forEach {
            it.method.invoke(key, netType)
        }

    }

    fun register(clazz: Any) {
        val java = clazz::class.java
        val methods = java.methods

        var list = ArrayList<MethodManager>()
        run breaking@{
            methods.forEach continuing@{
                val annotation = it.getAnnotation(Network::class.java) ?: return@continuing
                val parameterTypes = it.parameterTypes

                var methodManager = MethodManager(parameterTypes[0], annotation.netType, it)

                list.add(methodManager)
            }
        }


        map[clazz] = list


        Log.d("NetworkManager", map.toString())

    }


    fun unRegister(clazz: Any) {
        val arrayList = map[clazz]
        if (arrayList != null) {
            map.remove(clazz)
        }

        Log.d("NetworkManager", map.toString())
    }

}
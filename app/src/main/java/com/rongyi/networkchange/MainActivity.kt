package com.rongyi.networkchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rongyi.mylibrary.NetType
import com.rongyi.mylibrary.Network
import com.rongyi.mylibrary.NetworkManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        NetworkManager.instance.register(this)

    }

    @Network
    fun networkState(type: NetType) {
        when (type) {
            NetType.WIFI -> {
                Log.d("Main", "链接wifi随便浪")
            }
            NetType.MOBILE -> {
                Log.d("Main", "用流量,家里有矿")
            }
            else -> {
                Log.d("Main", "野鸡网络")
            }

        }
    }


    @Network(netType = NetType.WIFI)
    fun networkState2(type: NetType) {
        when (type) {
            NetType.WIFI -> {
                Log.d("Main22222", "链接wifi随便浪")
            }
            NetType.MOBILE -> {
                Log.d("Main22222", "用流量,家里有矿")
            }
            else -> {
                Log.d("Main22222", "野鸡网络")
            }

        }
    }


    fun networkState3(type: NetType) {

    }

    override fun onDestroy() {
        super.onDestroy()
        NetworkManager.instance.unRegister(this)
    }

}

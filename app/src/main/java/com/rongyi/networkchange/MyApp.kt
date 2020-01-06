package com.rongyi.networkchange

import android.app.Application
import com.rongyi.mylibrary.NetworkManager

class MyApp : Application() {


    override fun onCreate() {
        super.onCreate()

        NetworkManager.instance.initialization(this)

    }

}
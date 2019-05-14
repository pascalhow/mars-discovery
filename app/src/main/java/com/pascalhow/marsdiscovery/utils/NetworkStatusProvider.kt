package com.pascalhow.marsdiscovery.utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkStatusProvider(private val context: Context) {

    fun hasNetworkConnection(): Boolean {
        val connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectionManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}

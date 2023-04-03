package com.example.pokedex.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager

object WiFi {
    @SuppressLint("MissingPermission")
    @JvmStatic fun verifyWifiConnection(context: Context):Boolean{
        var bConnected = false
        val connection = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifiNetworks = connection.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobileData = connection.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifiNetworks?.state.toString() == "CONNECTED" || mobileData?.state.toString() == "CONNECTED"){
            bConnected = true
        }else{
            //context.toast(Resources.getSystem().getString(R.string.missing_connection))
        }
        return bConnected
    }

    @SuppressLint("MissingPermission")
    fun checkConnection(context: Context):Boolean{
        val connection = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifiNetworks = connection.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobileData = connection.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        return wifiNetworks?.state.toString() == "CONNECTED" || mobileData?.state.toString() == "CONNECTION"
    }
}
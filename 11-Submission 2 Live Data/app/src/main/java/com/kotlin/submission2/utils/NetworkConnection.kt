package com.kotlin.submission2.utils

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.net.*
import android.os.Build
import androidx.lifecycle.LiveData

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class NetworkConnection(private val context: Context) : LiveData<Boolean>() {

    private var connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    //    private lateinit var networkCallbacks: ConnectivityManager.NetworkCallback
    private lateinit var networkCallbacks: ConnectivityManager.NetworkCallback

    override fun onActive() {
        super.onActive()
        updateConnection()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                connectivityManager.registerDefaultNetworkCallback(connectivityManagerCallback())
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                lollipopNetworkRequest()
            }
            else -> {
                context.registerReceiver(
                    networkReceiver,
                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                )
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.unregisterNetworkCallback(networkCallbacks)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun lollipopNetworkRequest() {
        val requestBuilder = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        connectivityManager.registerNetworkCallback(
            requestBuilder.build(),
            connectivityManagerCallback()
        )
    }

    private fun connectivityManagerCallback(): ConnectivityManager.NetworkCallback {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            networkCallbacks = object : ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network) {
                    super.onLost(network)
                    postValue(false)
                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    postValue(true)
                }
            }
            return networkCallbacks
        } else {
            throw IllegalAccessError("Error")
        }
    }

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            updateConnection()
        }

    }

    private fun updateConnection() {
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        postValue(activeNetwork?.isConnected == true)
    }

//    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
//
//    override fun onActive() {
//        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
//        networkCallback = createNetworkCallback()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            cm.registerDefaultNetworkCallback(networkCallback)
//        }
//    }
//
//    override fun onInactive() {
//        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
//        cm.unregisterNetworkCallback(networkCallback)
//    }
//
//    private fun createNetworkCallback() = object : ConnectivityManager.NetworkCallback() {
//
//        override fun onAvailable(network: Network) {
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
//                postValue(true)
//            }
//        }
//
//        override fun onCapabilitiesChanged(
//            network: Network,
//            networkCapabilities: NetworkCapabilities
//        ) {
//            val isInternet = networkCapabilities.hasCapability(NET_CAPABILITY_INTERNET)
//            val isValidated = networkCapabilities.hasCapability(NET_CAPABILITY_VALIDATED)
//            postValue(isInternet && isValidated)
//        }
//
//        override fun onLost(network: Network) {
//            postValue(false)
//        }
//
//    }

}
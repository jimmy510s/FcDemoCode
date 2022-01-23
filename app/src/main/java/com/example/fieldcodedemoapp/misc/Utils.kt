package com.example.fieldcodedemoapp.misc

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import java.lang.Exception

class Utils {
    companion object {

        fun isNetworkAvailable(context: Context) =
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
                getNetworkCapabilities(activeNetwork)?.run {
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                } ?: false
            }

        fun toString(input: Any?): String? {
            return try {
                input?.toString() ?: ""
            } catch (e: Exception) {
                ""
            }
        }
    }
}
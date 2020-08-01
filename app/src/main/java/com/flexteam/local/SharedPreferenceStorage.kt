package com.flexteam.local

import android.content.Context
import com.flexteam.MainApplication
import com.google.gson.Gson

class SharedPreferenceStorage
constructor(private val context: Context, private val gson: Gson) : PreferenceStorage {

    companion object {

        @Synchronized
        fun getInstance(): SharedPreferenceStorage? {
            val context = MainApplication.getInstance().applicationContext
            return if (context != null) {
                SharedPreferenceStorage(context, Gson())
            } else {
                null
            }
        }
    }

}

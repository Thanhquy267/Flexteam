package com.example.flexteam.utils

import com.example.flexteam.MainApplication
import java.text.SimpleDateFormat
import java.util.*

object StringUtil {

    fun capitalize(s: String?): String {
        if (s == null || s.isEmpty()) {
            return ""
        }
        val first = s[0]
        return if (Character.isUpperCase(first)) {
            s
        } else {
            Character.toUpperCase(first) + s.substring(1)
        }
    }

    fun getString(stringId: Int): String {
        val context = MainApplication.getInstance().applicationContext
        return context?.getString(stringId) ?: ""
    }

    fun getStringWithValue(stringId: Int, value: Int): String {
        val context = MainApplication.getInstance().applicationContext
        return context?.getString(stringId, value) ?: ""
    }

    fun listStringToString(listString: ArrayList<String>?): String {
        var string = ""
        listString?.forEach {
            string += it
        }
        return string
    }

    fun toSimpleDateFormat(dateTime : String?) : String{
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val dateTimeFormat = SimpleDateFormat("dd-MM-yyyy / HH:mm", Locale.getDefault())
        return simpleDateFormat.format(dateTimeFormat.parse(dateTime.toString()) as Date)
    }
}// prevent init Util class

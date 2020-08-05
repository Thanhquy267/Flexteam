package com.flexteam.utils

import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.ObservableField
import com.flexteam.MainApplication
import com.google.android.material.textfield.TextInputEditText
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


fun String?.isValidEmail(): Boolean {
    return if (this?.isNotEmpty() == true) {
        android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    } else {
        false
    }
}

fun String?.isMatchPassword(repeatPassword: String?): Boolean {
    return if (this?.isEmpty() == true || repeatPassword?.isEmpty() == true) {
        false
    } else {
        this == repeatPassword
    }
}

fun String?.isValidPassword(): Boolean {
    if (this == null) return false
    if (this.isEmpty() || this.isBlank() || this.length < 6) return false
    return true
}


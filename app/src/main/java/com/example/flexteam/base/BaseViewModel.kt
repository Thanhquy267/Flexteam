package com.example.flexteam.base
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import com.example.flexteam.MainApplication

open class BaseViewModel : ViewModel() {

    protected val TAG = javaClass.simpleName

    var mActivityNavigator: ActivityNavigator? = null

    var isFullScreen = ObservableField<Boolean>(true)

    private var deepLinkViewModel: Uri? = null

    open fun onReady() {}

    fun getTextWatcher(observableField: ObservableField<String>, action: ((Unit) -> Unit)? = null): TextWatcher {
        return object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                observableField.set(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                action?.invoke(Unit)
            }

        }
    }
}
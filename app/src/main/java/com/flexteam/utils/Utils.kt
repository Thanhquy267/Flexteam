package com.flexteam.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.core.content.ContextCompat
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.util.Base64
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.Toast
import com.flexteam.MainApplication
import com.flexteam.R
import java.io.ByteArrayOutputStream
import java.util.*

class Utils {
    @Suppress("DEPRECATION")
    @SuppressLint("RestrictedApi")
    companion object {
        fun removeShiftMode(view: BottomNavigationView) {
            val menuView = view.getChildAt(0) as BottomNavigationMenuView
            try {
                val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
                shiftingMode.isAccessible = true
                shiftingMode.setBoolean(menuView, false)
                shiftingMode.isAccessible = false
                for (i in 0 until menuView.childCount) {
                    val item = menuView.getChildAt(i) as BottomNavigationItemView

//                    item_project.setShiftingMode(false)
                    item.setShifting(false)
                    // set once again checked value, so view will be updated

                    item.setChecked(item.itemData.isChecked)
                }
            } catch (e: Throwable) {
                Log.e("BottomNav", "Unable to get shift mode field", e)
            } catch (e: IllegalAccessException) {
                Log.e("BottomNav", "Unable to change value of shift mode", e)
            }
        }

        fun hideKeyboard(context: Context, view: View?) {
            if (view != null) {
                val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        fun showKeyboard(context: Context?, view: View?) {
            if (view != null) {
                val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
                inputMethodManager?.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
            }
        }

        fun showToast(message: String){
            Toast.makeText(MainApplication.getInstance().applicationContext,message,Toast.LENGTH_SHORT).show()
        }

        fun doShare(message: String = "Put whatever you want"): Intent {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, setHtmlText(message))
            return intent
        }

        fun makePhoneCall(context: Context, phoneNumber: String) {
            val phoneCallIntent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null))
            context.startActivity(phoneCallIntent)
        }

        fun changeDrawableColor(
            context: Context = MainApplication.getInstance().applicationContext,
            icon: Int,
            newColor: Int
        ): Drawable? {
            val mDrawable = ContextCompat.getDrawable(context, icon)?.mutate()
            mDrawable?.colorFilter =
                    PorterDuffColorFilter(ContextCompat.getColor(context, newColor), PorterDuff.Mode.SRC_ATOP)
            return mDrawable
        }

        fun getSpannableString(fullText: String?, subText: String?, what: Any?): SpannableString? {
            if (fullText == null || subText == null) return null
            val subTextIndex = fullText.indexOf(subText ?: "")
            if (subTextIndex > -1) {
                val spanText = SpannableString(fullText)
                spanText.setSpan(
                    what,
                    subTextIndex,
                    subTextIndex.plus(subText.length),
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )
                return spanText
            }
            return null
        }

        fun getDimension(resourceId: Int): Int {
            return MainApplication.getInstance().applicationContext.resources.getDimensionPixelOffset(resourceId)
        }

        fun getHorizontalPaddingFromCardView(
            cornerRadius: Int = getDimension(R.dimen._10sdp),
            elevationCard: Int = getDimension(R.dimen._10sdp)
        ): Int {
            val context = MainApplication.getInstance().applicationContext
            val res = context.resources
            return (elevationCard + (1 - Math.cos(45.0)) * cornerRadius).toInt()
        }

        fun getVerticalPaddingFromCardView(
            cornerRadius: Int = getDimension(R.dimen._10sdp),
            elevationCard: Int = getDimension(R.dimen._10sdp)
        ): Int {
            val context = MainApplication.getInstance().applicationContext
            val res = context.resources
            return (elevationCard * 1.5 + (1 - Math.cos(45.0)) * cornerRadius).toInt()
        }

        fun setHtmlText(bodyData: String?): Spanned {
            return if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                Html.fromHtml(bodyData, Html.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml(bodyData)
            }
        }

        fun getDeviceWidth(activity: Activity?): Int {
            val displayMetrics = DisplayMetrics()
            activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
            return displayMetrics.widthPixels
        }

        fun getScreenHeight(): Int {
            return Resources.getSystem().displayMetrics.heightPixels
        }

        fun getColor(id: Int): Int {
            return ContextCompat.getColor(MainApplication.getInstance().applicationContext, id)
        }

        fun getDrawable(id: Int): Drawable? {
            return ContextCompat.getDrawable(MainApplication.getInstance().applicationContext, id)
        }

        fun encodeToBase64(image: Bitmap?, compressFormat: Bitmap.CompressFormat, quality: Int): String {
            val byteArrayOS = ByteArrayOutputStream()
            image?.compress(compressFormat, quality, byteArrayOS)
            return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.NO_WRAP)
        }

        fun decodeBase64(input: String?): Bitmap {
            val decodedByte: ByteArray = Base64.decode(input, 0)
            return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
        }

        fun isPermissionGranted(
            grantPermissions: Array<String>, grantResults: IntArray,
            permission: String
        ): Boolean {
            for (i in grantPermissions.indices) {
                if (permission == grantPermissions[i]) {
                    return grantResults[i] == PackageManager.PERMISSION_GRANTED
                }
            }
            return false
        }
    }
}
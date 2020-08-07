package com.flexteam.base

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.flexteam.utils.Utils
import com.google.android.material.appbar.AppBarLayout


@Suppress("DEPRECATION")
@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    open val TAG = javaClass.simpleName

    var currentCountInStack = 0

    var mCurrentFragment: BaseFragment? = null
    var mFragmentAsContent: Fragment? = null

    private var mIsActivityRunning = true

    protected var mShowFullScreen: Boolean = false


    protected var mAppBar: AppBarLayout? = null
    protected var mToolbar: Toolbar? = null

    override fun onStop() {
        hideProgressDialog()
        super.onStop()
    }

    override fun onDestroy() {
        mIsActivityRunning = false
        hideSoftKeyboard()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        mIsActivityRunning = true
    }

    fun isRunning(): Boolean {
        return mIsActivityRunning
    }

    override fun onBackPressed() {
        Utils.hideKeyboard(this, this.currentFocus)
        if (mCurrentFragment is BaseFragment && mCurrentFragment?.doWantToFinishActivity() == true) {
            finish()
            return
        }
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            updateCurrentFragment()
            return
        }
        if (mCurrentFragment?.onBackPress() == true){
            return
        }
        super.onBackPressed()
    }

    fun updateCurrentFragment() { // only call in ActivityNavigator
        val fragmentManager = supportFragmentManager
        val fragment: Fragment? = if (fragmentManager.backStackEntryCount > 0) {
            val fragmentTag =
                fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).name
            fragmentManager.findFragmentByTag(fragmentTag)
        } else {
            try {
                fragmentManager.fragments[0]
            } catch (e: IndexOutOfBoundsException) {
                null
            }
        }
        if (fragment is BaseFragment) {
            mCurrentFragment = fragment
        }
    }

    override fun onBackStackChanged() {
        if (isMultiClicked() || currentCountInStack < fragmentManager.backStackEntryCount) return
        currentCountInStack = fragmentManager.backStackEntryCount
        Log.d("TEST_MULTICLICKED", System.currentTimeMillis().toString())
        updateCurrentFragment()
    }

    open fun hideSoftKeyboard() {
        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private var lastClickTime: Long = 0

    private var mLongClickedTime = 50
    // preventing double, using threshold of 700 ms
    open fun isMultiClicked(): Boolean {
        val now = SystemClock.elapsedRealtime()
        if (now - lastClickTime < mLongClickedTime) {
            Log.w(TAG, "onClick Clicked too quickly: " + (now - lastClickTime) + " ms")
            //            lastClickTime = now;
            return true
        }
        Log.i(TAG, "onClick Clicked too quickly pass: $mLongClickedTime")
        lastClickTime = now
        return false
    }


    private var progressDialog: Dialog? = null
    fun showProgressDialog() {
        if (!mIsActivityRunning) return
        hideProgressDialog()
//        progressDialog = DialogUtils.progressDialog(this)
        progressDialog!!.show()
    }

    fun hideProgressDialog() {
        if (!mIsActivityRunning) return
        if (progressDialog != null) {
            try {
                progressDialog!!.dismiss()
            } catch (ignored: Exception) {
                ignored.printStackTrace()
            }
        }
    }

    fun finishWithResult(isResultOK: Boolean = true, intent: Intent? = null) {
        val result = if (isResultOK) Activity.RESULT_OK else Activity.RESULT_CANCELED
        setResult(result, intent)
        finish()
    }

    fun setUpFullScreenView() {
        mShowFullScreen = true
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    fun showFragmentAsContent(fragment: Fragment) {
        mFragmentAsContent = fragment
        supportFragmentManager.beginTransaction().add(android.R.id.content, mFragmentAsContent!!)
            .commit()
    }

    fun removeContentFragment() {
        if (mFragmentAsContent != null) {
            supportFragmentManager.beginTransaction().remove(mFragmentAsContent!!).commit()
            mFragmentAsContent = null
        }
    }

    fun finishWithoutAnimation() {
        finish()
        this.overridePendingTransition(0, android.R.anim.fade_out)
    }
}
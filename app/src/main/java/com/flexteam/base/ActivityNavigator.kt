package com.flexteam.base

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.flexteam.R

class ActivityNavigator(val mActivity: BaseActivity?, var mFragment: BaseFragment? = null) {
    fun finishWithResult(isResultOK: Boolean = true, intent: Intent? = null) {
        val result = if (isResultOK) Activity.RESULT_OK else Activity.RESULT_CANCELED
        mActivity?.setResult(result, intent)
        mActivity?.finish()
    }

    fun startActivity(activity : BaseActivity){
        val intent = Intent(mActivity,activity::class.java)
        mActivity?.startActivity(intent)
    }

    fun finishActivity(){
        mActivity?.finish()
    }

    fun addFragment(
        containerId: Int, fragment: BaseFragment?, shouldAddStack: Boolean,
        enter: Int = R.anim.slide_in_left, exit: Int = R.anim.slide_out_left,
        popEnter: Int = R.anim.slide_in_right, popExit: Int = R.anim.slide_out_right
    ) {
        val fragmentManager = mActivity?.supportFragmentManager
        val ft = fragmentManager?.beginTransaction()
        if (enter > 0 || exit > 0 || popEnter > 0 || popExit > 0) {
            ft?.setCustomAnimations(enter, exit, popEnter, popExit)
        }
        if (fragment != null) {
//            val defaultContainerId = R.id.fl_fragment_container
            ft?.replace(containerId, fragment as Fragment, fragment.javaClass.simpleName)
            if (shouldAddStack) {
                ft?.addToBackStack(fragment.javaClass.simpleName)
            }
            mActivity?.mCurrentFragment = fragment
            mActivity?.supportFragmentManager?.addOnBackStackChangedListener(mActivity)
        }
        ft?.commitAllowingStateLoss()
//        mActivity?.supportFragmentInjector()?.inject(fragment)
    }

//    fun replaceFragment(
//        containerId: Int, fragment: BaseFragment?, enter: Int = R.anim.slide_in_left,
//        exit: Int = R.anim.slide_out_left, popEnter: Int = R.anim.slide_in_right,
//        popExit: Int = R.anim.slide_out_right
//    ) {
//        val fragmentManager = mActivity?.supportFragmentManager
//        val ft = fragmentManager?.beginTransaction()
//        if (enter > 0 || exit > 0 || popEnter > 0 || popExit > 0) {
//            ft?.setCustomAnimations(enter, exit, popEnter, popExit)
//        }
//        if (fragment != null) {
//            ft?.replace(containerId, fragment as Fragment, fragment.javaClass.simpleName)
////            ft?.addToBackStack(fragment.javaClass.simpleName)
//            mActivity?.mCurrentFragment = fragment
//        }
//        ft?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//        ft?.commit()
//    }

//    fun changeFragment(
//        containerId: Int, fragment: BaseFragment?, enter: Int = R.anim.slide_in_left,
//        exit: Int = R.anim.slide_out_left, popEnter: Int = R.anim.slide_in_right,
//        popExit: Int = R.anim.slide_out_right
//    ) {
//        val fragmentManager = mActivity?.supportFragmentManager
//        val ft = fragmentManager?.beginTransaction()
//        if (enter > 0 || exit > 0 || popEnter > 0 || popExit > 0) {
//            ft?.setCustomAnimations(enter, exit, popEnter, popExit)
//        }
//        if (fragment != null) {
//            ft?.replace(containerId, fragment as Fragment, fragment.javaClass.simpleName)
//            ft?.addToBackStack(fragment.javaClass.simpleName)
//            mActivity?.mCurrentFragment = fragment
//        }
//        ft?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//        ft?.commit()
//    }

    fun popToRootFragment() {
        mActivity?.supportFragmentManager?.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        mActivity?.updateCurrentFragment()
    }

    fun popFragment() {
        mActivity?.supportFragmentManager?.popBackStack()
        mActivity?.updateCurrentFragment()
    }

    fun popFragmentBack(){
        mActivity?.onBackPressed()
        mActivity?.updateCurrentFragment()
    }

    fun popToSpecificFragment(className: String) {
        mActivity?.supportFragmentManager?.popBackStack(
            className,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        mActivity?.updateCurrentFragment()
    }

    fun showFragmentAsContent(fragment: Fragment) {
        mActivity?.supportFragmentManager?.beginTransaction()?.add(android.R.id.content, fragment)
            ?.commit()
        mActivity?.mFragmentAsContent = fragment
    }

    fun removeContentFragment() {
        if (mActivity?.mFragmentAsContent != null) {
            mActivity.supportFragmentManager.beginTransaction()
                .remove(mActivity.mFragmentAsContent!!).commit()
            mActivity.mFragmentAsContent = null
        }
    }


    private fun startActivity(
        intent: Intent?,
        requestCode: Int = -1,
        showProgress: Boolean = true
    ) {
        if (mActivity?.isRunning() == false || intent == null) return
        //Testing Crash
//        Crashlytics.getInstance().crash()
        if (showProgress) {
            mActivity?.runOnUiThread {
                mActivity.showProgressDialog()
            }
        }
        if (requestCode == -1) {
            if (mFragment == null)
                mActivity?.startActivity(intent)
            else
                mFragment?.startActivity(intent)
        } else {
            if (mFragment == null)
                mActivity?.startActivityForResult(intent, requestCode)
            else
                mFragment?.startActivityForResult(intent, requestCode)
        }
    }

    fun showToast(message : String) {
        Toast.makeText(mActivity?.applicationContext,message,Toast.LENGTH_SHORT).show()
    }


    fun showLoading() {
        mActivity?.showProgressDialog()
    }

    fun hideLoading() {
        mActivity?.hideProgressDialog()
    }
}
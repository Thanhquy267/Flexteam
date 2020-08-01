package com.flexteam.base

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.DaggerFragment

abstract class BaseFragment : DaggerFragment() {
    open val TAG = javaClass.simpleName

    protected var mActivity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as? BaseActivity
    }

    open fun doWantToFinishActivity(): Boolean {
        return false
    }

}
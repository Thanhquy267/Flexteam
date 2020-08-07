package com.flexteam.base

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    open val TAG = javaClass.simpleName

    protected var mActivity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as? BaseActivity
    }

    open fun doWantToFinishActivity(): Boolean {
        return false
    }

    open fun onBackPress() : Boolean{
        return false
    }

}
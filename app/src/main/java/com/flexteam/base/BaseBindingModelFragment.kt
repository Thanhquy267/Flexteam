package com.flexteam.base
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders

abstract class BaseBindingModelFragment<VBD: ViewDataBinding,
        VM: BaseViewModel>: BaseFragment() {

    lateinit var mLayoutBinding: VBD
    lateinit var mViewModel: VM

    abstract fun layoutId(): Int
    abstract fun viewModelClass(): Class<VM>
    abstract fun bindingVariable(): Int

    private lateinit var mRootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG,"onCreateView")
        mLayoutBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        if (bindingVariable() != -1) {
            mViewModel = ViewModelProviders.of(this).get(viewModelClass())
//            mViewModel.mUserManager = mActivity?.mUserManager
            mViewModel.mActivityNavigator = ActivityNavigator(mActivity, this)
            mLayoutBinding.setVariable(bindingVariable(), mViewModel)
        }
        mRootView = mLayoutBinding.root
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        mViewModel.onReady()
    }

    open fun setupView() {
    }

    open fun reloadView() {
        setupView()
        mViewModel.onReady()
    }
}
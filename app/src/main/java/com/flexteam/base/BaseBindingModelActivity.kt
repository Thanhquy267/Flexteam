package com.flexteam.base

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.flexteam.utils.Utils

abstract class BaseBindingModelActivity<VBD : ViewDataBinding,
        VM : BaseViewModel> : BaseActivity() {

    lateinit var mLayoutBinding: VBD
    protected lateinit var mViewModel: VM

    private var backPressTimer: Long = 0

    // abstract
    abstract fun layoutId(): Int

    abstract fun viewModelClass(): Class<VM>
    open fun bindingVariable(): Int {
        return -1
    }

    private var isNewIntent: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = ""
        //
        mLayoutBinding = DataBindingUtil.setContentView(this, layoutId())
        //
        setupLayoutView()
        //
        if (bindingVariable() != -1) {
            mViewModel = ViewModelProviders.of(this).get(viewModelClass())
            mViewModel.mActivityNavigator = ActivityNavigator(this)
            mViewModel.onReady()
            mLayoutBinding.setVariable(bindingVariable(), mViewModel)
        }
        mLayoutBinding.executePendingBindings()
        setupView()
        retrieveExtras()
    }

    private fun setupLayoutView() {
        if (mShowFullScreen) {
            val view = mLayoutBinding.root
            val viewHeight = Utils.getScreenHeight()
            val layoutParams = when (view.parent) {
                is ConstraintLayout -> LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, viewHeight)
                is RelativeLayout -> RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    viewHeight
                )
                is LinearLayout -> LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    viewHeight
                )
                else -> FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, viewHeight)
            }
            //
            view.layoutParams = layoutParams
        }
    }

    open fun setupView() {
    }

    open fun retrieveExtras() {
    }
}
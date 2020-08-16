package com.flexteam.feature.home.profile

import com.flexteam.R
import com.flexteam.base.BaseViewModel
import com.flexteam.customview.ActionBarViewModel
import com.flexteam.utils.StringUtil
import io.reactivex.functions.Consumer

class ProfileViewModel : BaseViewModel() {
   var mActionBarViewModel = ActionBarViewModel()
    override fun onReady() {
        super.onReady()
        setupActionBar()
    }

    private fun setupActionBar(){
        mActionBarViewModel.mTitle.set(StringUtil.getString(R.string.profile_title))
        mActionBarViewModel.mShouldShowStartIcon.set(true)
        mActionBarViewModel.mStartIconClickConsumer= Consumer {
            mActivityNavigator?.popFragmentBack()
        }
    }
}
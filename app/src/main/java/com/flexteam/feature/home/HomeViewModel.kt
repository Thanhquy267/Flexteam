package com.flexteam.feature.home

import com.flexteam.R
import com.flexteam.base.BaseViewModel
import com.flexteam.customview.ActionBarViewModel
import com.flexteam.utils.StringUtil

class HomeViewModel: BaseViewModel() {
    val mActionBarViewModel = ActionBarViewModel()

    override fun onReady() {
        super.onReady()
        mActionBarViewModel.mTitle.set(StringUtil.getString(R.string.chat_title))
    }
}
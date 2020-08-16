package com.flexteam.feature.home

import android.content.Context
import android.content.Intent
import com.flexteam.R
import com.flexteam.base.BaseViewModel
import com.flexteam.customview.ActionBarViewModel
import com.flexteam.feature.home.container.ContainerActivity
import com.flexteam.type.ContainerType
import com.flexteam.utils.StringUtil
import io.reactivex.functions.Consumer

class HomeViewModel : BaseViewModel() {
    val mActionBarViewModel = ActionBarViewModel()
    var mContext: Context? = null
    var mCurrentTab = TabType.TabChat.value

    override fun onReady() {
        super.onReady()
        setupActionbar()
    }

    private fun setupActionbar() {
        mActionBarViewModel.mTitle.set(StringUtil.getString(R.string.chat_title))
        mActionBarViewModel.mShouldShowStartIcon.set(true)
        mActionBarViewModel.mStartIconClickConsumer = Consumer {
            when(mCurrentTab){
                TabType.TabChat.value ->{
                    val intent = Intent(mContext, ContainerActivity::class.java)
                    val bundle = ContainerActivity.createDataBundle(ContainerType.NOTIFICATION.value)
                    intent.putExtras(bundle)
                    mActivityNavigator?.mActivity?.startActivity(intent)
                }
                TabType.TabProject.value ->{
                }
                TabType.TabSchedule.value ->{
                }
                TabType.TabTeamList.value ->{
                }
            }
        }
    }
}
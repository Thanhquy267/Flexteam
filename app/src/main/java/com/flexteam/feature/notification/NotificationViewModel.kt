package com.flexteam.feature.notification

import com.flexteam.base.BaseViewModel
import com.flexteam.customview.ActionBarViewModel
import com.flexteam.model.NotificationModel

class NotificationViewModel: BaseViewModel() {
    var mListNotification = ArrayList<NotificationModel>()
    var mNotificationAdapter: NotificationAdapter? = null
    var mActionBarViewModel = ActionBarViewModel()

    override fun onReady() {
        super.onReady()
        mActionBarViewModel.mTitle.set("Notifications")
    }

    fun dummyData(){
        mListNotification.add(NotificationModel().apply {
            title = "Đức Lê đã nhắc tới bạn"
            description = "Đức Lê đã nhắc tới bạn"
            tag = "@All"
        })
        mListNotification.add(NotificationModel().apply {
            title = "Đức Lê đã nhắc tới bạn"
            description = "Đức Lê đã nhắc tới bạn"
            tag = "@All"
        })
    }
}
package com.flexteam.feature.notification

import com.flexteam.base.BaseViewModel
import com.flexteam.model.NotificationModel

class NotificationViewModel: BaseViewModel() {
    var mListNotification = ArrayList<NotificationModel>()
    var mNotificationAdapter: NotificationAdapter? = null
}
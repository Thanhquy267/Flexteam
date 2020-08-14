package com.flexteam.feature.home.schedule

import androidx.databinding.ObservableField
import com.flexteam.R
import com.flexteam.base.BaseViewModel
import com.flexteam.model.ScheduleType
import com.flexteam.utils.StringUtil
import io.reactivex.functions.Consumer
import java.util.*
import kotlin.collections.ArrayList

class ScheduleViewModel: BaseViewModel() {
    lateinit var mScheduleAdapter: ScheduleAdapter
    var mScheduleData = ArrayList<String>()
    var mSelectMode = ""
    var mShowFab = ObservableField(false)
    var mShowUpdate = ObservableField(false)

    var mListSelected = ArrayList<Pair<Int, String>>()
    var mListOfOldData = ArrayList<Pair<Int, String>>()

    var mDateDisplayText = ObservableField("")
    var mSelectedDate = Date()
    var mOnDateClickedConsumer : Consumer<Unit>? = null

    fun update() {
        //Update here
    }

    fun onDateClicked() {
        mOnDateClickedConsumer?.accept(Unit)
    }

    init {
        mScheduleData.add(ScheduleType.AVAILABLE.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.NOT_PROVIDED.value)
        mScheduleData.add(ScheduleType.AVAILABLE.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.NOT_PROVIDED.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.AVAILABLE.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.AVAILABLE.value)
        mScheduleData.add(ScheduleType.AVAILABLE.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.AVAILABLE.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.NOT_PROVIDED.value)
        mScheduleData.add(ScheduleType.NOT_PROVIDED.value)
        mScheduleData.add(ScheduleType.AVAILABLE.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.NOT_PROVIDED.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.NOT_PROVIDED.value)
        mScheduleData.add(ScheduleType.NOT_PROVIDED.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.AVAILABLE.value)
        mScheduleData.add(ScheduleType.AVAILABLE.value)
        mScheduleData.add(ScheduleType.AVAILABLE.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.AVAILABLE.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.AVAILABLE.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.NOT_PROVIDED.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.NOT_PROVIDED.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.NOT_PROVIDED.value)
        mScheduleData.add(ScheduleType.NOT_PROVIDED.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.NOT_PROVIDED.value)
        mScheduleData.add(ScheduleType.NOT_PROVIDED.value)
        mScheduleData.add(ScheduleType.NOT_PROVIDED.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.BUSY.value)
        mScheduleData.add(ScheduleType.BUSY.value)

        mDateDisplayText.set(StringUtil.getString(R.string.today_txt))
    }
}
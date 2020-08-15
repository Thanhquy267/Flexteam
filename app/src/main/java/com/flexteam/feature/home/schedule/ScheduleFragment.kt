package com.flexteam.feature.home.schedule

import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.view.animation.OvershootInterpolator
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexteam.BR
import com.flexteam.R
import com.flexteam.base.BaseBindingModelFragment
import com.flexteam.databinding.FragmentScheduleBinding
import com.flexteam.model.ScheduleType
import com.flexteam.utils.Constant
import com.flexteam.utils.StringUtil
import com.flexteam.utils.Utils
import com.michaelflisar.dragselectrecyclerview.DragSelectTouchListener
import com.michaelflisar.dragselectrecyclerview.DragSelectionProcessor
import io.reactivex.functions.Consumer
import java.util.*
import kotlin.collections.ArrayList

class ScheduleFragment : BaseBindingModelFragment<FragmentScheduleBinding, ScheduleViewModel>(),
    ScheduleItemListener {
    private lateinit var mDragSelectTouchListener: DragSelectTouchListener
    private lateinit var mOnDragSelectionListener: DragSelectionProcessor
    private var mSelection: MutableSet<Int> = hashSetOf()
    private var mSelected = false

    override fun layoutId(): Int = R.layout.fragment_schedule
    override fun viewModelClass(): Class<ScheduleViewModel> = ScheduleViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
        setUpRecyclerView()
        setUpFab()
        setUpDatePicker()
    }

    private fun setUpDatePicker() {
        val toDayCalendar = Calendar.getInstance()

        val currentYear = toDayCalendar.get(Calendar.YEAR)
        val currentMonth = toDayCalendar.get(Calendar.MONTH)
        val currentDay = toDayCalendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                if (toDayCalendar.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR) && toDayCalendar.get(
                        Calendar.YEAR
                    ) == calendar.get(Calendar.YEAR)
                ) {
                    mViewModel.mDateDisplayText.set(StringUtil.getString(R.string.today_txt))
                } else {
                    mViewModel.mDateDisplayText.set(
                        Utils.dateToString(
                            calendar.time,
                            Constant.DAY_OF_WEEK_FULL_FORMAT
                        )
                    )
                }
                mViewModel.mSelectedDate = calendar.time
            }, currentYear, currentMonth, currentDay
        )
        mViewModel.mOnDateClickedConsumer = Consumer {
            datePickerDialog.show()
        }
    }

    private fun setUpFab() {
        mLayoutBinding.fabEdit.setOnClickListener {
            if (mViewModel.mShowFab.get() == true) {//Hide fab
                mViewModel.mShowFab.set(false)
                hideFab()
                rollBackData()
                //Reset edit mode
                mViewModel.mSelectMode = ""
                mLayoutBinding.fabBusy.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.grey_b7
                    )
                )
                mLayoutBinding.fabAvailable.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.grey_b7
                    )
                )
            } else { //Show fab
                mViewModel.mShowFab.set(true)
                showFab()
            }
        }
        //
        mLayoutBinding.fabBusy.setOnClickListener {
            mLayoutBinding.fabBusy.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.busy))
            mLayoutBinding.fabAvailable.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.grey_b7))
            mViewModel.mSelectMode = ScheduleType.BUSY.value
            rollBackData()
        }
        mLayoutBinding.fabAvailable.setOnClickListener {
            mLayoutBinding.fabAvailable.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.available))
            mLayoutBinding.fabBusy.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.grey_b7))
            mViewModel.mSelectMode = ScheduleType.AVAILABLE.value
            rollBackData()
        }
        //
    }

    private fun rollBackData() {
        if (!mViewModel.mListSelected.isNullOrEmpty()) {
            mViewModel.mListOfOldData.forEach {
                mViewModel.mScheduleAdapter.setDataAt(it.first, it.second)
            }
            mViewModel.mListSelected.clear()
            mViewModel.mListOfOldData.clear()
        }
        mViewModel.mShowUpdate.set(!mViewModel.mListSelected.isNullOrEmpty())
    }

    private fun showFab() {
        ViewCompat.animate(mLayoutBinding.fabAvailable)
            .translationY(requireContext().resources.getDimensionPixelOffset(R.dimen._minus55sdp).toFloat())
            .withLayer()
            .setDuration(200L)
            .setInterpolator(OvershootInterpolator(0.5F))
            .start()

        ViewCompat.animate(mLayoutBinding.fabBusy)
            .translationY(requireContext().resources.getDimensionPixelOffset(R.dimen._minus55sdp).toFloat())
            .withLayer()
            .setDuration(500L)
            .setInterpolator(OvershootInterpolator(0.5F))
            .start()
    }

    private fun hideFab() {
        ViewCompat.animate(mLayoutBinding.fabAvailable)
            .translationY(0F)
            .withLayer()
            .setDuration(400L)
            .setInterpolator(OvershootInterpolator(0.5F))
            .start()

        ViewCompat.animate(mLayoutBinding.fabBusy)
            .translationY(0F)
            .withLayer()
            .setDuration(200L)
            .setInterpolator(OvershootInterpolator(0.5F))
            .start()
    }

    override fun OnScheduleLongClick(position: Int) {
        if (mViewModel.mSelectMode.isEmpty())
            return
        mDragSelectTouchListener.startDragSelection(position)
    }

    private fun setUpRecyclerView() {
        mOnDragSelectionListener =
            DragSelectionProcessor(object : DragSelectionProcessor.ISelectionHandler {
                override fun getSelection(): MutableSet<Int> {
                    return mSelection
                }

                override fun isSelected(index: Int): Boolean {
                    return mSelected
                }

                override fun updateSelection(
                    start: Int,
                    end: Int,
                    isSelected: Boolean,
                    calledFromOnStart: Boolean
                ) {
                    mSelected = isSelected
                    val foundOldData = mViewModel.mListOfOldData.find {
                        it.first == start
                    }
                    if (foundOldData != null) {
                        //If this row has already been selected -> set data back to old data
                        mViewModel.mScheduleAdapter.setDataAt(start, foundOldData.second)
                        mViewModel.mListOfOldData.remove(foundOldData)
                        //Remove index in list selected data
                        val item = mViewModel.mListSelected.find {
                            it.first == start
                        }
                        if (item != null)
                            mViewModel.mListSelected.remove(item)
                    } else {
                        //If this row has not been selected -> set new data
                        mViewModel.mListOfOldData.add(
                            Pair(
                                start,
                                mViewModel.mScheduleAdapter.getDataAt(start)
                            )
                        )
                        mViewModel.mScheduleAdapter.setDataAt(start, mViewModel.mSelectMode)
                        //Add index to list selected
                        mViewModel.mListSelected.add(Pair(start, mViewModel.mSelectMode))
                    }
                    mViewModel.mShowUpdate.set(!mViewModel.mListSelected.isNullOrEmpty())
                }
            }).withMode(DragSelectionProcessor.Mode.FirstItemDependentToggleAndUndo)

        mDragSelectTouchListener = DragSelectTouchListener()
            .withSelectListener(mOnDragSelectionListener)

        mViewModel.mScheduleAdapter = ScheduleAdapter(ArrayList(), this)
        mLayoutBinding.rvData.layoutManager = LinearLayoutManager(requireContext())
        mLayoutBinding.rvData.adapter = mViewModel.mScheduleAdapter
        mViewModel.mScheduleAdapter.setData(mViewModel.mScheduleData)

        mLayoutBinding.rvData.addOnItemTouchListener(mDragSelectTouchListener)
    }
}
package com.flexteam.feature.home.schedule

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.flexteam.R
import com.flexteam.databinding.LayoutItemScheduleBinding
import com.flexteam.model.ScheduleType
import com.flexteam.utils.StringUtil

class ScheduleAdapter(
    private var listData: ArrayList<String>,
    private val listener: ScheduleItemListener
) :
    RecyclerView.Adapter<ScheduleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_item_schedule,
                parent,
                false
            ),
            listener
        )
    }

    override fun getItemCount(): Int {
        return 48
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bindData(listData[position])
    }

    fun setData(data: ArrayList<String>) {
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    fun setDataAt(position: Int, type: String) {
        listData[position] = type
        notifyItemChanged(position)
    }

    fun getDataAt(position: Int): String {
        return listData[position]
    }
}

class ScheduleViewHolder(
    private val binding: LayoutItemScheduleBinding,
    private val listener: ScheduleItemListener
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindData(availableType: String) {
        setTime()
        binding.root.setOnLongClickListener {
            listener.OnScheduleLongClick(adapterPosition)
            true
        }
        when(availableType) {
            ScheduleType.AVAILABLE.value -> {
                setViewColor(R.color.available)
                binding.tvAvailableType.text = StringUtil.getString(R.string.available_type_available)
            }
            ScheduleType.BUSY.value -> {
                setViewColor(R.color.busy)
                binding.tvAvailableType.text = StringUtil.getString(R.string.available_type_busy)
            }
            else -> {
                setViewColor(R.color.grey_b7)
                binding.tvAvailableType.text = StringUtil.getString(R.string.available_type_not_provided)
            }
        }

        ViewCompat.animate(binding.rlView)
            .translationX(binding.root.context.resources.getDimensionPixelOffset(R.dimen._25sdp).toFloat())
            .alpha(1F)
            .withLayer()
            .setDuration(300L)
            .setInterpolator(OvershootInterpolator(5.0F))
            .start()
    }

    private fun setViewColor(color: Int) {
        binding.ivView.setColorFilter(ContextCompat.getColor(binding.root.context, color))
    }

    @SuppressLint("SetTextI18n")
    private fun setTime() {
        when (adapterPosition) {
            0 -> binding.tvTime.text = "00:00"
            1 -> binding.tvTime.text = "00:30"
            2 -> binding.tvTime.text = "01:00"
            3 -> binding.tvTime.text = "01:30"
            4 -> binding.tvTime.text = "02:00"
            5 -> binding.tvTime.text = "02:30"
            6 -> binding.tvTime.text = "03:00"
            7 -> binding.tvTime.text = "03:30"
            8 -> binding.tvTime.text = "04:00"
            9 -> binding.tvTime.text = "04:30"
            10 -> binding.tvTime.text = "05:00"
            11 -> binding.tvTime.text = "05:30"
            12 -> binding.tvTime.text = "06:00"
            13 -> binding.tvTime.text = "06:30"
            14 -> binding.tvTime.text = "07:00"
            15 -> binding.tvTime.text = "07:30"
            16 -> binding.tvTime.text = "08:00"
            17 -> binding.tvTime.text = "08:30"
            18 -> binding.tvTime.text = "09:00"
            19 -> binding.tvTime.text = "09:30"
            20 -> binding.tvTime.text = "10:00"
            21 -> binding.tvTime.text = "10:30"
            22 -> binding.tvTime.text = "11:00"
            23 -> binding.tvTime.text = "11:30"
            24 -> binding.tvTime.text = "12:00"
            25 -> binding.tvTime.text = "12:30"
            26 -> binding.tvTime.text = "13:00"
            27 -> binding.tvTime.text = "13:30"
            28 -> binding.tvTime.text = "14:00"
            29 -> binding.tvTime.text = "14:30"
            30 -> binding.tvTime.text = "15:00"
            31 -> binding.tvTime.text = "15:30"
            32 -> binding.tvTime.text = "16:00"
            33 -> binding.tvTime.text = "16:30"
            34 -> binding.tvTime.text = "17:00"
            35 -> binding.tvTime.text = "17:30"
            36 -> binding.tvTime.text = "18:00"
            37 -> binding.tvTime.text = "18:30"
            38 -> binding.tvTime.text = "19:00"
            39 -> binding.tvTime.text = "19:30"
            40 -> binding.tvTime.text = "20:00"
            41 -> binding.tvTime.text = "20:30"
            42 -> binding.tvTime.text = "21:00"
            43 -> binding.tvTime.text = "21:30"
            44 -> binding.tvTime.text = "22:00"
            45 -> binding.tvTime.text = "22:30"
            46 -> binding.tvTime.text = "23:00"
            47 -> binding.tvTime.text = "23:30"
        }
    }
}

interface ScheduleItemListener {
    fun OnScheduleLongClick(position: Int)
}
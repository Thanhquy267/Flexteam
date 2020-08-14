package com.flexteam.feature.home

import android.view.animation.OvershootInterpolator
import androidx.core.view.ViewCompat
import com.flexteam.BR
import com.flexteam.R
import com.flexteam.adapter.AppViewPagerAdapter
import com.flexteam.base.BaseBindingModelActivity
import com.flexteam.databinding.ActivityHomeBinding
import com.flexteam.feature.home.chat.ChatListFragment
import com.flexteam.feature.home.schedule.ScheduleFragment
import com.flexteam.utils.Utils
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : BaseBindingModelActivity<ActivityHomeBinding, HomeViewModel>() {
    private var mIsFabClicked = false

    override fun layoutId(): Int = R.layout.activity_home
    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
        setFabClicked()
        setUpViewPager()
    }

    private fun setUpViewPager() {
        val viewPagerAdapter = AppViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFrag(ChatListFragment(), "Chat")
        viewPagerAdapter.addFrag(ChatListFragment(), "Chat")
        viewPagerAdapter.addFrag(ChatListFragment(), "Chat")
        viewPagerAdapter.addFrag(ScheduleFragment(), "Schedule")

        mLayoutBinding.vpHome.adapter = viewPagerAdapter
        mLayoutBinding.vpHome.offscreenPageLimit = 3
    }

    private fun setFabClicked() {
        mLayoutBinding.fabMore.setOnClickListener {
            mIsFabClicked = !mIsFabClicked
            if (mIsFabClicked)
                rotateFabForward()
            else
                rotateFabBackward()
        }
        mLayoutBinding.fab1.setOnClickListener {
            Utils.showToast("fab1 clicked!!!!")
        }
    }

    private fun rotateFabForward() {
        ViewCompat.animate(mLayoutBinding.fabMore)
                .rotation(135.0F)
                .withLayer()
                .setDuration(500)
                .setInterpolator(OvershootInterpolator(5.0F))
                .start()
        expandSubFabs()
    }

    private fun rotateFabBackward() {
        ViewCompat.animate(mLayoutBinding.fabMore)
                .rotation(0.0F)
                .withLayer()
                .setDuration(500)
                .setInterpolator(OvershootInterpolator(5.0F))
                .start()
        collapseSubFabs()
    }

    private fun translateFab(fab: FloatingActionButton, x: Float, y: Float) {
        ViewCompat.animate(fab)
                .rotation(if (x == 0.0F && y == 0.0F) 0.0F else 360.0F)
                .translationY(y)
                .translationX(x)
                .withLayer()
                .setDuration(500)
                .setInterpolator(OvershootInterpolator(2.0F))
                .start()
    }

    private fun expandSubFabs() {
        translateFab(mLayoutBinding.fab1, 0.0F, -220.0F)
        translateFab(mLayoutBinding.fab2, 125.0F, -175.0F)
        translateFab(mLayoutBinding.fab3, -125.0F, -175.0F)
        translateFab(mLayoutBinding.fab4, 200.0F, -70.0F)
        translateFab(mLayoutBinding.fab5, -200.0F, -70.0F)
    }

    private fun collapseSubFabs() {
        translateFab(mLayoutBinding.fab1, 0.0F, 0.0F)
        translateFab(mLayoutBinding.fab2, 0.0F, 0.0F)
        translateFab(mLayoutBinding.fab3, 0.0F, 0.0F)
        translateFab(mLayoutBinding.fab4, 0.0F, 0.0F)
        translateFab(mLayoutBinding.fab5, 0.0F, 0.0F)
    }
}
package com.flexteam.feature.home

import android.content.Intent
import android.view.animation.OvershootInterpolator
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.ViewPager
import com.flexteam.BR
import com.flexteam.R
import com.flexteam.adapter.MainViewPagerAdapter
import com.flexteam.base.BaseBindingModelActivity
import com.flexteam.databinding.ActivityHomeBinding
import com.flexteam.feature.home.chat.ChatListFragment
import com.flexteam.feature.home.container.ContainerActivity
import com.flexteam.feature.home.project.ProjectFragment
import com.flexteam.feature.home.schedule.ScheduleFragment
import com.flexteam.type.ContainerType
import com.flexteam.utils.StringUtil
import com.flexteam.utils.Utils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.functions.Consumer

class HomeActivity : BaseBindingModelActivity<ActivityHomeBinding, HomeViewModel>() {

    private var mIsFabClicked = false

    override fun layoutId(): Int = R.layout.activity_home
    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
        setFabClicked()
        setUpViewPager()
        handleBottomNavigator()
        mLayoutBinding.layoutActionBar.viewModel = mViewModel.mActionBarViewModel
    }

    private fun setUpViewPager() {
        val mainPagerAdapter = MainViewPagerAdapter(supportFragmentManager)
        mainPagerAdapter.addFrag(ChatListFragment())
        mainPagerAdapter.addFrag(ProjectFragment())
        mainPagerAdapter.addFrag(ScheduleFragment())
        mainPagerAdapter.addFrag(ChatListFragment())
        mLayoutBinding.vpMain.adapter = mainPagerAdapter
        mLayoutBinding.vpMain.offscreenPageLimit = mainPagerAdapter.count
        handleTabSelected(TabType.TabChat)
    }

    private fun handleBottomNavigator() {
        mLayoutBinding.ivTabChat.setOnClickListener {
            mLayoutBinding.vpMain.currentItem = 0
        }
        mLayoutBinding.ivProject.setOnClickListener {
            mLayoutBinding.vpMain.currentItem = 1
        }
        mLayoutBinding.ivTabSchedule.setOnClickListener {
            mLayoutBinding.vpMain.currentItem = 2
        }
        mLayoutBinding.ivTabTeamList.setOnClickListener {
            mLayoutBinding.vpMain.currentItem = 3
        }
        mLayoutBinding.vpMain.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                when(position){
                    TabType.TabChat.value -> handleTabSelected(TabType.TabChat)
                    TabType.TabProject.value -> handleTabSelected(TabType.TabProject)
                    TabType.TabSchedule.value -> handleTabSelected(TabType.TabSchedule)
                    TabType.TabTeamList.value -> handleTabSelected(TabType.TabTeamList)
                }
            }
        })
    }

    private fun handleTabSelected(tab: TabType) {
        when (tab.value) {
            TabType.TabChat.value -> {
                mLayoutBinding.ivTabChat.setColorFilter(ContextCompat.getColor(baseContext, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN)
                mLayoutBinding.ivProject.setColorFilter(ContextCompat.getColor(baseContext, R.color.normalText), android.graphics.PorterDuff.Mode.SRC_IN)
                mLayoutBinding.ivTabSchedule.setColorFilter(ContextCompat.getColor(baseContext, R.color.normalText), android.graphics.PorterDuff.Mode.SRC_IN)
                mLayoutBinding.ivTabTeamList.setColorFilter(ContextCompat.getColor(baseContext, R.color.normalText), android.graphics.PorterDuff.Mode.SRC_IN)
                //
                mViewModel.mActionBarViewModel.mTitle.set(StringUtil.getString(R.string.chat_title))
                mViewModel.mActionBarViewModel.mStartIconClickConsumer = Consumer {
                    val intent = Intent(this, ContainerActivity::class.java)
                    val bundle = ContainerActivity.createDataBundle(ContainerType.NOTIFICATION.value)
                    intent.putExtras(bundle)
                    mViewModel.mActivityNavigator?.mActivity?.startActivity(intent)
                }
            }
            TabType.TabProject.value -> {
                mLayoutBinding.ivTabChat.setColorFilter(ContextCompat.getColor(baseContext, R.color.normalText), android.graphics.PorterDuff.Mode.SRC_IN)
                mLayoutBinding.ivProject.setColorFilter(ContextCompat.getColor(baseContext, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN)
                mLayoutBinding.ivTabSchedule.setColorFilter(ContextCompat.getColor(baseContext, R.color.normalText), android.graphics.PorterDuff.Mode.SRC_IN)
                mLayoutBinding.ivTabTeamList.setColorFilter(ContextCompat.getColor(baseContext, R.color.normalText), android.graphics.PorterDuff.Mode.SRC_IN)
                //
                mViewModel.mActionBarViewModel.mTitle.set(StringUtil.getString(R.string.project_title))
            }
            TabType.TabSchedule.value -> {
                mLayoutBinding.ivTabChat.setColorFilter(ContextCompat.getColor(baseContext, R.color.normalText), android.graphics.PorterDuff.Mode.SRC_IN)
                mLayoutBinding.ivProject.setColorFilter(ContextCompat.getColor(baseContext, R.color.normalText), android.graphics.PorterDuff.Mode.SRC_IN)
                mLayoutBinding.ivTabSchedule.setColorFilter(ContextCompat.getColor(baseContext, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN)
                mLayoutBinding.ivTabTeamList.setColorFilter(ContextCompat.getColor(baseContext, R.color.normalText), android.graphics.PorterDuff.Mode.SRC_IN)
                //
                mViewModel.mActionBarViewModel.mTitle.set(StringUtil.getString(R.string.schedule_title))
            }
            TabType.TabTeamList.value -> {
                mLayoutBinding.ivTabChat.setColorFilter(ContextCompat.getColor(baseContext, R.color.normalText), android.graphics.PorterDuff.Mode.SRC_IN)
                mLayoutBinding.ivProject.setColorFilter(ContextCompat.getColor(baseContext, R.color.normalText), android.graphics.PorterDuff.Mode.SRC_IN)
                mLayoutBinding.ivTabSchedule.setColorFilter(ContextCompat.getColor(baseContext, R.color.normalText), android.graphics.PorterDuff.Mode.SRC_IN)
                mLayoutBinding.ivTabTeamList.setColorFilter(ContextCompat.getColor(baseContext, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN)
                //
                mViewModel.mActionBarViewModel.mTitle.set(StringUtil.getString(R.string.team_list_title))
            }
        }
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
        mLayoutBinding.fab2.setOnClickListener {
            Utils.showToast("fab2 clicked!!!!")
        }
        mLayoutBinding.fab3.setOnClickListener {
            Utils.showToast("fab3 clicked!!!!")
        }
        mLayoutBinding.fab4.setOnClickListener {
            Utils.showToast("fab4 clicked!!!!")
        }
        mLayoutBinding.fab5.setOnClickListener {
            Utils.showToast("fab5 clicked!!!!")
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

enum class TabType(val value: Int) {
    TabChat(0),
    TabProject(1),
    TabSchedule(2),
    TabTeamList(3)
}
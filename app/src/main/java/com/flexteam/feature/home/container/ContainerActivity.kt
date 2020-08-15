package com.flexteam.feature.home.container

import android.os.Bundle
import com.flexteam.BR
import com.flexteam.R
import com.flexteam.base.BaseBindingModelActivity
import com.flexteam.databinding.ActivityContainerBinding
import com.flexteam.feature.notification.NotificationFragment
import com.flexteam.type.ContainerType

class ContainerActivity : BaseBindingModelActivity<ActivityContainerBinding, ContainerViewModel>() {
    private var mContainerType = 0

    override fun layoutId(): Int = R.layout.activity_container
    override fun viewModelClass(): Class<ContainerViewModel> = ContainerViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent != null) {
            mContainerType = intent.extras?.getInt(TYPE_EXTRA) ?: ContainerType.NOTIFICATION.value
        }
        when (mContainerType) {
            ContainerType.NOTIFICATION.value -> mViewModel.mActivityNavigator?.addFragment(
                R.id.rl_root,
                NotificationFragment.newInstance(),
                shouldAddStack = false
            )
        }
    }

    companion object {
        private const val TYPE_EXTRA = "TYPE_EXTRA"

        fun createDataBundle(type: Int): Bundle {
            val bundle = Bundle()
            bundle.putInt(TYPE_EXTRA, type)
            return bundle
        }
    }
}
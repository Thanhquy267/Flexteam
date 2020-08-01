package com.flexteam.feature

import androidx.lifecycle.ViewModel
import com.flexteam.di.FragmentScoped
import com.flexteam.di.ViewModelKey
import com.flexteam.feature.login.LoginFragment
import com.flexteam.feature.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AuthModule {
//    @FragmentScoped
//    @ContributesAndroidInjector
//    internal abstract fun provideLoginFragment(): LoginFragment
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(LoginViewModel::class)
//    internal abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    internal abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel
}
package com.example.flexteam.feature

import androidx.lifecycle.ViewModel
import com.example.flexteam.di.FragmentScoped
import com.example.flexteam.di.ViewModelKey
import com.example.flexteam.feature.login.LoginFragment
import com.example.flexteam.feature.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AuthModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun provideLoginFragment(): LoginFragment

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
}
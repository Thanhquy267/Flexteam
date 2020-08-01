package com.flexteam.di

import androidx.lifecycle.ViewModelProvider
import com.flexteam.di.CoreViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: CoreViewModelFactory): ViewModelProvider.Factory
}

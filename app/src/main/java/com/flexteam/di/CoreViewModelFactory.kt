package com.flexteam.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * ViewModelFactory which uses Dagger to create the instances.
 */
class CoreViewModelFactory @Inject
constructor(private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        for ((key, value) in creators) {
            if (modelClass.isAssignableFrom(key)) {
                return value.get() as T
            }
        }
        throw IllegalArgumentException("unknown model class $modelClass")
    }
}

package com.example.flexteam.di

import android.content.Context
import com.example.flexteam.MainApplication
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import com.example.flexteam.local.PreferenceStorage
import com.example.flexteam.local.SharedPreferenceStorage

/**
 * Defines all the classes that need to be provided in the scope of the app.
 *
 *
 * Define here all objects that are shared throughout the app, like SharedPreferences, navigators or
 * others. If some of those objects are singletons, they should be annotated with `@Singleton`.
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun providePreferenceStorage(context: Context, gson: Gson): PreferenceStorage {
        return SharedPreferenceStorage(context, gson)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        //        gsonBuilder.setDateFormat(DATE_FORMAT);
        return gsonBuilder.create()
    }

}

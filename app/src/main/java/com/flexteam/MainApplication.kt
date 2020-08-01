package com.flexteam
import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.flexteam.utils.Constant
import io.realm.Realm
import io.realm.RealmConfiguration

class MainApplication: Application() {
    companion object {

        private lateinit var mInstance: MainApplication

        fun getInstance(): MainApplication {
            return mInstance
        }
        const val TAG = "MainApplication"
    }
    var activityStack = arrayListOf<String>()

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        initRealm()
        initActivityLifeCycle()
    }

    private fun initRealm() {
        Realm.init(applicationContext)
        val realmConfiguration = RealmConfiguration.Builder()
            .name(Constant.REALM_DB_NAME)
            .schemaVersion(Constant.REALM_SCHEMA_VERSION)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    private fun initActivityLifeCycle() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks{
            override fun onActivityPaused(activity: Activity?) {
            }

            override fun onActivityResumed(activity: Activity?) {
            }

            override fun onActivityStarted(activity: Activity?) {
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityDestroyed(activity: Activity?) {
                if (activity != null) activityStack.remove(activity::class.java.simpleName)
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                if (activity != null) {
                    activityStack.add(activity::class.java.simpleName)
                }
            }
        })
    }
}
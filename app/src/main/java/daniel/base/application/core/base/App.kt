package daniel.base.application.core.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import daniel.base.application.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class App : Application(){

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}

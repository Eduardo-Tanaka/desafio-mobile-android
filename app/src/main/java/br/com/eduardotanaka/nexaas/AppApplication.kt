package br.com.eduardotanaka.nexaas

import br.com.eduardotanaka.nexaas.di.DaggerAppComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.DaggerApplication
import timber.log.Timber

class AppApplication : DaggerApplication() {

    private val appComponent = DaggerAppComponent.factory().create(this)
    override fun applicationInjector() = appComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Threeten DateTime initialization
        AndroidThreeTen.init(this)
    }
}
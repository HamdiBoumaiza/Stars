package com.hb.stars

import android.app.Application
import com.facebook.stetho.Stetho
import com.hb.stars.di.component.AppComponent
import com.hb.stars.di.component.DaggerAppComponent

open class StarWarsApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
        initStetho()
    }

    private fun initDI() {
        appComponent = DaggerAppComponent.builder().build()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
    }
}

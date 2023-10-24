package com.example.roulette.application

import android.app.Application
import com.onesignal.OneSignal

class OneSignalApplication:Application() {
    val ONESIGNALL_APP_ID = "47aa0cf5-9ce6-46a4-939a-19f3bf5ecba6"
    override fun onCreate() {
        super.onCreate()
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNALL_APP_ID)
    }
}
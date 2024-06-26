package com.bw.composetodo

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp

/**
    Application Level Dependency Container
    add "name" in manifest
 */
@HiltAndroidApp
class ToDoApplication: Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
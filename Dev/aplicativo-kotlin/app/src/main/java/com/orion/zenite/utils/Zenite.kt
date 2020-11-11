package com.orion.zenite.utils

import android.app.Application

class Zenite : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPreferencias.init(this)
    }
}
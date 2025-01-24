package com.example.finalprojectpam

import android.app.Application
import com.example.finalprojectpam.dependenciesinjection.AppContainer
import com.example.finalprojectpam.dependenciesinjection.DefaultAppContainer

class AcaraApplications: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}
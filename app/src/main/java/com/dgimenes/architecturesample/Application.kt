package com.dgimenes.architecturesample

import android.app.Application
import com.dgimenes.architecturesample.di.BaseGraphComponent
import com.dgimenes.architecturesample.di.ContextModule
import com.dgimenes.architecturesample.di.DaggerBaseGraphComponent

class Application: Application() {

    companion object {
        lateinit var graphComponent: BaseGraphComponent
    }

    override fun onCreate() {
        super.onCreate()
        setupDIGraph()
    }

    private fun setupDIGraph() {
        graphComponent = DaggerBaseGraphComponent
                .builder()
                .contextModule(ContextModule(applicationContext))
                .build()
    }

}
package com.dgimenes.architecturesample

import android.app.Application
import com.dgimenes.architecturesample.di.BaseGraphComponent
import com.dgimenes.architecturesample.di.ContextModule
import com.dgimenes.architecturesample.di.DaggerBaseGraphComponent
import com.dgimenes.architecturesample.web.MOVIEDB_BASEURL

class Application: Application() {

    companion object {
        lateinit var graphComponent: BaseGraphComponent
    }

    var webServerBaseUrl: String = MOVIEDB_BASEURL

    override fun onCreate() {
        super.onCreate()
        setupDIGraph()
    }

    private fun setupDIGraph() {
        graphComponent = DaggerBaseGraphComponent
                .builder()
                .contextModule(ContextModule(this))
                .build()
    }

}
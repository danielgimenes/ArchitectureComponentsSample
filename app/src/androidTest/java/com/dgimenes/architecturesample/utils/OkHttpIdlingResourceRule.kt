package com.dgimenes.architecturesample.utils

import android.support.test.espresso.IdlingRegistry
import com.dgimenes.architecturesample.Application
import com.jakewharton.espresso.OkHttp3IdlingResource
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class OkHttpIdlingResourceRule : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {

            override fun evaluate() {
                val idlingResource = OkHttp3IdlingResource.create(
                        "okhttp", Application.graphComponent.okHttpClient())
                IdlingRegistry.getInstance().register(idlingResource)

                base.evaluate()

                IdlingRegistry.getInstance().unregister(idlingResource)
            }
        }
    }

}
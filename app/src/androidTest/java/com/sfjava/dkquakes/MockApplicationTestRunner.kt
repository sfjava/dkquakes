package com.sfjava.dkquakes

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class MockApplicationTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, DKQuakesTestApplication::class.qualifiedName, context)
    }
}

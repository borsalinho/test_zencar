package com.example.test.app

import android.app.Application
import com.example.test.data.ProfileDb

class MyApp : Application() {
    val database by lazy {
        ProfileDb.createDataBase(this)
    }
}
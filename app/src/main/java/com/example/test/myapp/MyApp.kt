package com.example.test.myapp

import android.app.Application
import com.example.test.data.storage.ProfileDb

class MyApp : Application() {
    val database by lazy {
        ProfileDb.createDataBase(this)
    }
}
package com.example.test.presentation.myapp

import android.app.Application
import com.example.test.data.ProfileRepositoryImpl
import com.example.test.data.profilepreferences.ProfilePreferences
import com.example.test.data.storage.ProfileDb

class MyApp : Application() {
    lateinit var profileRepository: ProfileRepositoryImpl
    private lateinit var profilePreferences: ProfilePreferences

    override fun onCreate() {
        super.onCreate()

        val database = ProfileDb.createDataBase(this)
        profilePreferences = ProfilePreferences(this)
        profileRepository = ProfileRepositoryImpl(database.profileDao, profilePreferences)
    }
}
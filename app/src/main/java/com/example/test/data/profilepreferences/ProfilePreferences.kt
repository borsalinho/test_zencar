package com.example.test.data.profilepreferences

import android.content.Context
import android.content.SharedPreferences

class ProfilePreferences(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    var isLoggedIn: Boolean
        get() = prefs.getBoolean("is_logged_in", false)
        set(value) {
            prefs.edit().putBoolean("is_logged_in", value).apply()
        }

}
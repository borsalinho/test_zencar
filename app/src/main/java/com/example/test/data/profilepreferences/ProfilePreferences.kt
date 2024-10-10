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

    var loggedInUserId: Int
        get() = prefs.getInt("logged_in_user_id", -1)
        set(value) {
            prefs.edit().putInt("logged_in_user_id", value).apply()
        }
}
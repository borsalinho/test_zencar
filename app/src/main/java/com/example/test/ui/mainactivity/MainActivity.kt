package com.example.test.ui.mainactivity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.test.ui.composes.LoginScreen
import com.example.test.ui.composes.ProfilesScreen
import com.example.test.ui.composes.RegistrationScreen
import com.example.test.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userPreferences = UserPreferences(this)
        val isLoggedIn = userPreferences.isLoggedIn
        
        setContent {
            TestTheme{
                var isRegistrationScreen by remember { mutableStateOf(false) }
                var toLogin by remember { mutableStateOf(false) }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (isLoggedIn || toLogin) {
                        ProfilesScreen(
                            onLogout = {
                                userPreferences.isLoggedIn = false
                                toLogin = false
                            }
                        )
                    } else {
                        when (isRegistrationScreen) {
                            true -> RegistrationScreen(
                                onSwitchToLogin = { isRegistrationScreen = false }
                            )
                            false -> LoginScreen(
                                onSwitchToRegistration = { isRegistrationScreen = true },
                                onLoginSuccess = {
                                    toLogin = true
                                    userPreferences.isLoggedIn = true
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

class UserPreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    var isLoggedIn: Boolean
        get() = prefs.getBoolean("is_logged_in", false)
        set(value) {
            prefs.edit().putBoolean("is_logged_in", value).apply()
        }
}


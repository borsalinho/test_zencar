package com.example.test.ui.mainactivity

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
import com.example.test.ui.composes.RegistrationScreen
import com.example.test.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme{
                var isRegistrationScreen by remember { mutableStateOf(false) }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    when (isRegistrationScreen) {
                        true -> RegistrationScreen(
                            onSwitchToLogin = { isRegistrationScreen = false }
                        )
                        false -> LoginScreen(
                            onSwitchToRegistration = { isRegistrationScreen = true }
                        )
                    }
                }
            }
        }
    }
}


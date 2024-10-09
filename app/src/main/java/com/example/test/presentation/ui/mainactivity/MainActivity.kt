package com.example.test.presentation.ui.mainactivity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.test.presentation.intent.ProfileUIIntent
import com.example.test.presentation.myapp.MyApp
import com.example.test.presentation.ui.composes.LoginScreen
import com.example.test.presentation.ui.composes.ProfilesScreen
import com.example.test.presentation.ui.composes.RegistrationScreen
import com.example.test.presentation.ui.theme.TestTheme
import com.example.test.presentation.viewmodel.ProfileUIViewModel
import com.example.test.presentation.viewmodel.ProfileUIViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: ProfileUIViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as MyApp
        val viewModelFactory = ProfileUIViewModelFactory(app.profileRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileUIViewModel::class.java)

        viewModel.handleIntent(ProfileUIIntent.CheckLoginStatus)

        setContent {
            TestTheme{
                val state by viewModel.state.collectAsState()

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (state.isLoggedIn) {
                        ProfilesScreen(
                            viewModel
                        )
                    } else {
                        when (state.isRegistrationScreen) {
                            true -> RegistrationScreen(
                                viewModel,
                                onSwitchToLogin = {
                                    viewModel.handleIntent(ProfileUIIntent.SwitchToLogin)
                                }
                            )
                            false -> LoginScreen(
                                viewModel,
                                onSwitchToRegistration = {
                                    viewModel.handleIntent(ProfileUIIntent.SwitchToRegistration)
                                },
                                onLoginSuccess = {
                                    viewModel.handleIntent(ProfileUIIntent.LoadProfilesUI)
                                }
                            )

                        }
                    }
                }
            }
        }
    }
}



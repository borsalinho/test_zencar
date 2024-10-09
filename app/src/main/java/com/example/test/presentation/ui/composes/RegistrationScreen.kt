package com.example.test.presentation.ui.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.test.presentation.intent.ProfileUIIntent
import com.example.test.presentation.model.ProfileUI
import com.example.test.presentation.viewmodel.ProfileUIViewModel


@Composable
fun RegistrationScreen(
    viewModel: ProfileUIViewModel,
    onSwitchToLogin: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        TextField(
            value = state.regLogin,
            onValueChange = {
                viewModel.handleIntent(ProfileUIIntent.UpdateRegLogin(it))
            },
            label = {
                Text(text = "Name")
            }
        )
        TextField(
            value = state.regPassword,
            onValueChange = {
                viewModel.handleIntent(ProfileUIIntent.UpdateRegPassword(it))
            },
            label = {
                Text(text = "Password")
            }
        )
        TextField(
            value = state.regBDay,
            onValueChange = {
                viewModel.handleIntent(ProfileUIIntent.UpdateRegBDay(it))
            },
            label = {
                Text(text = "Name...")
            }
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = {
                viewModel.handleIntent(
                    ProfileUIIntent.AddProfileEntity(
                        ProfileUI(
                            userName = state.regLogin,
                            userPassword = state.regPassword,
                            userBDay = state.regBDay
                        )
                    )
                )
            }
        ) {
            Text(text = "Зарегистрироваться")
        }
        TextButton(onClick = onSwitchToLogin) {
            Text(text = "Уже есть аккаунт? Войти")
        }

        state.loginError?.let {
            Text(text = it, color = state.messageColor)
        }
    }
}


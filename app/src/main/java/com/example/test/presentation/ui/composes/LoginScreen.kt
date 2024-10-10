package com.example.test.presentation.ui.composes

import android.util.Log
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.test.presentation.intent.ProfileUIIntent
import com.example.test.presentation.model.ProfileUI
import com.example.test.presentation.viewmodel.ProfileUIViewModel

@Composable
fun LoginScreen(
    viewModel: ProfileUIViewModel,
    onSwitchToRegistration: () -> Unit,
    onLoginSuccess: () -> Unit
){
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        TextField(
            value = state.login,
            onValueChange = {
                viewModel.handleIntent(ProfileUIIntent.UpdateLogin(it))
                viewModel.handleIntent(ProfileUIIntent.UpdateLoginError(null))
            },
            label = {
                Text(text = "Login")
            }
        )
        TextField(
            value = state.password,
            onValueChange = {
                viewModel.handleIntent(ProfileUIIntent.UpdatePassword(it))
                viewModel.handleIntent(ProfileUIIntent.UpdateLoginError(null))
            },
            label = {
                Text(text = "Password")
            },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = {
                viewModel.handleIntent(ProfileUIIntent.Login(
                    ProfileUI(
                        userName = state.login,
                        userPassword =  state.password,
                        userBDay = "")
                    )
                )
            }
        ) {
            Text(text = "Войти")
        }
        TextButton(onClick = onSwitchToRegistration) {
            Text(text = "Зарегистрироваться")
        }
        if (state.isLoggedIn) {
            onLoginSuccess()
        }

        state.loginError?.let {
            Text(text = it, color = state.messageColor)
        }
    }
}
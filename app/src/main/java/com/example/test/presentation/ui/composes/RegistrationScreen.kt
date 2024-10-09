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

    val datePattern = Regex("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$")

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
            onValueChange = { newDate ->
                viewModel.handleIntent(ProfileUIIntent.UpdateRegBDay(newDate))

                if (newDate.isNotEmpty() && !datePattern.matches(newDate)) {
                    viewModel.handleIntent(ProfileUIIntent.UpdateLoginError("Введите дату в формате дд-мм-гггг"))
                } else {
                    viewModel.handleIntent(ProfileUIIntent.UpdateLoginError(null))
                }
            },
            label = {
                Text(text = "Bday DD-MM-YYYY")
            }
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = {
                if (datePattern.matches(state.regBDay)) {
                    viewModel.handleIntent(
                        ProfileUIIntent.AddProfileEntity(
                            ProfileUI(
                                userName = state.regLogin,
                                userPassword = state.regPassword,
                                userBDay = state.regBDay
                            )
                        )
                    )
                } else {
                    viewModel.handleIntent(ProfileUIIntent.UpdateLoginError("Введите корректную дату!"))
                }
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


package com.example.test.ui.composes

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.ui.viewmodel.MainViewModel

@Composable
fun LoginScreen(
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory),
    onSwitchToRegistration: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        TextField(
            value = mainViewModel.login.value,
            onValueChange = {
                mainViewModel.login.value = it
            },
            label = {
                Text(text = "Login")
            }
        )
        TextField(
            value = mainViewModel.loginPassword.value,
            onValueChange = {
                mainViewModel.loginPassword.value = it
            },
            label = {
                Text(text = "Password")
            }
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = {

            }
        ) {
            Text(text = "Войти")
        }
        TextButton(onClick = onSwitchToRegistration) {
            Text(text = "Зарегистрироваться")
        }
    }
}
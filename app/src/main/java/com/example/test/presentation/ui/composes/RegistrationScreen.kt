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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.presentation.viewmodel.ProfileUIViewModel


@Composable
fun RegistrationScreen(
    viewModel: ProfileUIViewModel,
    onSwitchToLogin: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
//        TextField(
//            value = profileUIViewModel.newProfileName.value,
//            onValueChange = {
//                profileUIViewModel.newProfileName.value = it
//            },
//            label = {
//                Text(text = "Name")
//            }
//        )
//        TextField(
//            value = profileUIViewModel.newProfilePassword.value,
//            onValueChange = {
//                profileUIViewModel.newProfilePassword.value = it
//            },
//            label = {
//                Text(text = "Password")
//            }
//        )
//        TextField(
//            value = profileUIViewModel.newProfileBDay.value,
//            onValueChange = {
//                profileUIViewModel.newProfileBDay.value = it
//            },
//            label = {
//                Text(text = "Name...")
//            }
//        )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = {
//                profileUIViewModel.insertProfile()
            }
        ) {
            Text(text = "Зарегистрироваться")
        }
        TextButton(onClick = onSwitchToLogin) {
            Text(text = "Уже есть аккаунт? Войти")
        }
    }
}


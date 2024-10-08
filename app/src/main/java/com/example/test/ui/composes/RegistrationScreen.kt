package com.example.test.ui.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.ui.viewmodel.MainViewModel


@Composable
fun RegistrationScreen(
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        TextField(
            value = mainViewModel.newProfileName.value,
            onValueChange = {
                mainViewModel.newProfileName.value = it
            },
            label = {
                Text(text = "Name")
            }
        )
        TextField(
            value = mainViewModel.newProfilePassword.value,
            onValueChange = {
                mainViewModel.newProfilePassword.value = it
            },
            label = {
                Text(text = "Password")
            }
        )
        TextField(
            value = mainViewModel.newProfileBDay.value,
            onValueChange = {
                mainViewModel.newProfileBDay.value = it
            },
            label = {
                Text(text = "Name...")
            }
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = {
                mainViewModel.insertProfile()
            }
        ) {
            Text(text = "Зарегистрироваться")
        }
    }
}


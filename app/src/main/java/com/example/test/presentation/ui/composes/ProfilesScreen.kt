package com.example.test.presentation.ui.composes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.presentation.intent.ProfileUIIntent
import com.example.test.presentation.viewmodel.ProfileUIViewModel

@Composable
fun ProfilesScreen(
    viewModel: ProfileUIViewModel
) {
    val state by viewModel.state.collectAsState()

    Column {
        Button(onClick = {
            viewModel.handleIntent(ProfileUIIntent.Logout)
        }) {
            Text(text = "Выход")
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(state.profiles) { profile ->
                    ProfileItem(
                        profile,
                        onDeleteClick = {
                            viewModel.handleIntent(ProfileUIIntent.DeleteProfileEntity(profile))
                        }
                    )
                }
            }
        }
    }
}
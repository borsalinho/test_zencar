package com.example.test.presentation.ui.composes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.presentation.intent.ProfileUIIntent
import com.example.test.presentation.viewmodel.ProfileUIViewModel

@Composable
fun ProfilesScreen(
    viewModel: ProfileUIViewModel
) {
    val state by viewModel.state.collectAsState()

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Профили", modifier = Modifier.padding(16.dp))
            Button(onClick = {
                viewModel.handleIntent(ProfileUIIntent.Logout)
            }) {
                Text(text = "Выход")
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                val sortedProfiles = state.profiles.sortedBy { it.id }
                items(sortedProfiles) { profile ->
                    ProfileItem(
                        profile,
                        onDeleteClick = {
                            viewModel.handleIntent(ProfileUIIntent.DeleteProfileEntity(profile))
                        },
                        isDeleteEnabled = profile.id!! > state.loggedInUserId,
                        isHighlighted = profile.id == state.loggedInUserId
                    )
                }
            }
        }
    }
}
package com.example.test.ui.composes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.ui.viewmodel.MainViewModel

@Composable
fun ProfilesScreen(
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
) {
    val profilesList = mainViewModel.profileList.collectAsState(initial = emptyList())

    LazyColumn(
    modifier = Modifier.fillMaxWidth()
    ) {
        items(profilesList.value){ item ->
            ProfileItem(item){
                mainViewModel.deleteProfile(it)
            }
        }
    }
}
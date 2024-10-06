package com.example.test.ui.composes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.ui.viewmodel.MainViewModel


@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
) {
    val profilesList = mainViewModel.profileList.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) { 
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = mainViewModel.newName.value,
                onValueChange = {
                    mainViewModel.newName.value = it
                },
                label = {
                    Text(text = "Name...")
                }
            )
            IconButton(
                onClick = {
                    mainViewModel.insertProfile()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
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
}
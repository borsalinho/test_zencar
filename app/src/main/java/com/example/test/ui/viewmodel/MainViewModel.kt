package com.example.test.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.app.MyApp
import com.example.test.data.ProfileDb
import com.example.test.data.ProfileEntity
import kotlinx.coroutines.launch

class MainViewModel(
    val profileDb : ProfileDb
) : ViewModel() {

    companion object{
        val factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as MyApp).database
                return MainViewModel(database) as T
            }
        }
    }

    val profileList = profileDb.profileDao.getAllProfiles()
    val newName = mutableStateOf("")


    fun insertProfile() = viewModelScope.launch {
        val newProfile = ProfileEntity(name = newName.value)
        profileDb.profileDao.insertProfile(newProfile)
        newName.value = ""
        Log.d("MyLog", "Добавлено  ${newProfile.name}")
    }

    fun deleteProfile(profileEntity: ProfileEntity) = viewModelScope.launch {
        profileDb.profileDao.deleteProfile(profileEntity)
        Log.d("MyLog", "Удалено  ${profileEntity.name}")
    }


}
package com.example.test.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.test.myapp.MyApp
import com.example.test.data.storage.ProfileDb
import com.example.test.data.storage.ProfileEntity
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
    val newProfileName = mutableStateOf("")
    val newProfilePassword = mutableStateOf("")
    val newProfileBDay = mutableStateOf("")

    val login =  mutableStateOf("")
    val loginPassword =  mutableStateOf("")

    fun isProfileExists(username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val exists = profileDb.profileDao.checkProfile(username, password)
            onResult(exists)
        }
    }


    fun insertProfile() = viewModelScope.launch {
        try {
            val newProfile = ProfileEntity(
                userName = newProfileName.value,
                userPassword = newProfilePassword.value,
                userBDay = newProfileBDay.value
            )
            profileDb.profileDao.insertProfile(newProfile)
            CleanValues()
            Log.d("MyLog", "Добавлено  ${newProfile.userName}")
        } catch (e: Exception) {
            Log.e("Error", "Ошибка при добавлении профиля: ${e.message}")
        }
    }

    fun deleteProfile(profileEntity: ProfileEntity) = viewModelScope.launch {
        profileDb.profileDao.deleteProfile(profileEntity)
        Log.d("MyLog", "Удалено  ${profileEntity.userName}")
    }

    private fun CleanValues(){
        newProfileName.value = ""
        newProfilePassword.value = ""
        newProfileBDay.value = ""
    }
}
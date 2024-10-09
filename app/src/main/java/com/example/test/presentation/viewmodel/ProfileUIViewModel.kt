package com.example.test.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.ProfileRepositoryImpl
import com.example.test.presentation.intent.ProfileUIIntent
import com.example.test.presentation.mapper.toProfile
import com.example.test.presentation.mapper.toProfileUI
import com.example.test.presentation.model.ProfileUI
import com.example.test.presentation.state.ProfileUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileUIViewModel(
    private val profileRepository: ProfileRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileUIState())
    val state: StateFlow<ProfileUIState> = _state.asStateFlow()

    fun handleIntent(intent: ProfileUIIntent) {
        when (intent) {
            is ProfileUIIntent.LoadProfilesUI -> {
                loadProfiles()
            }

            is ProfileUIIntent.AddProfileEntity -> {
                addProfile(intent.profile)
            }

            is ProfileUIIntent.DeleteProfileEntity -> {
                deleteProfile(intent.profile)
            }

            is ProfileUIIntent.Login -> {
                login(intent.profile)
            }

            is ProfileUIIntent.Logout -> {
                viewModelScope.launch {
                    profileRepository.setLoggedIn(false)
                    _state.value = _state.value.copy(isLoggedIn = false)
                }
            }

            is ProfileUIIntent.CheckLoginStatus -> {
                viewModelScope.launch {
                    val isLoggedIn = profileRepository.isLoggedIn()
                    _state.value = _state.value.copy(isLoggedIn = isLoggedIn)
                }
            }

            is ProfileUIIntent.SwitchToRegistration -> {
                _state.value = _state.value.copy(isRegistrationScreen = true)
            }

            is ProfileUIIntent.SwitchToLogin -> {
                _state.value = _state.value.copy(isRegistrationScreen = false)
            }

            is ProfileUIIntent.UpdateLogin -> {
                _state.value = _state.value.copy(login = intent.login)
            }

            is ProfileUIIntent.UpdatePassword -> {
                _state.value = _state.value.copy(password = intent.password)
            }

            is ProfileUIIntent.UpdateLoginError -> {
                _state.value = _state.value.copy(loginError = intent.error)
            }
        }
    }

    private fun loadProfiles() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            profileRepository.getAllProfiles().collect { profiles ->
                _state.value = _state.value.copy(
                    profiles = profiles.map { it.toProfileUI() },
                    isLoading = false
                )
            }
        }
    }

    private fun addProfile(profileUI: ProfileUI) {
        viewModelScope.launch {
            profileRepository.insertProfile(profileUI.toProfile())
            loadProfiles()
        }
    }

    private fun deleteProfile(profileUI: ProfileUI) {
        viewModelScope.launch {
            profileRepository.deleteProfile(profileUI.toProfile())
            loadProfiles()
        }
    }

    private fun login(profile : ProfileUI) {
        viewModelScope.launch {
            val exists = profileRepository.checkProfile(profile.toProfile())
            _state.value = _state.value.copy(isLoggedIn = exists, loginError = if (!exists) "Неверный логин или пароль" else null)
        }
    }

    private fun updateLoginError(error: String?) {
        _state.value = _state.value.copy(loginError = error)
    }
}
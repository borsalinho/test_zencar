package com.example.test.presentation.viewmodel

import androidx.compose.ui.graphics.Color
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
                _state.value = _state.value.copy(
                    isRegistrationScreen = true,
                    loginError = null,
                    messageColor = Color.Red // костыль наше все
                )
            }

            is ProfileUIIntent.SwitchToLogin -> {
                _state.value = _state.value.copy(
                    isRegistrationScreen = false,
                    loginError = null,
                    messageColor = Color.Red
                )
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

            is ProfileUIIntent.UpdateRegBDay -> {
                _state.value = _state.value.copy(regBDay = intent.regBday)
            }
            is ProfileUIIntent.UpdateRegLogin -> {
                _state.value = _state.value.copy(regLogin = intent.regLogin)
            }
            is ProfileUIIntent.UpdateRegPassword -> {
                _state.value = _state.value.copy(regPassword = intent.regPassword)
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
            try {
                profileRepository.insertProfile(profileUI.toProfile())
                loadProfiles()
                updateLoginError("Вы успешно зарегистрировались!", Color.Green)
            } catch (e: Exception) {
                updateLoginError("Ошибка при регистрации: ${e.message}",  Color.Red)
            }

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

    private fun updateLoginError(error: String?, color: Color) {
        _state.value = _state.value.copy(loginError = error, messageColor = color)
    }
}
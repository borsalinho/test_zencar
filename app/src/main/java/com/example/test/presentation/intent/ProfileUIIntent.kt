package com.example.test.presentation.intent

import com.example.test.presentation.model.ProfileUI

sealed class ProfileUIIntent {
    object LoadProfilesUI : ProfileUIIntent()
    object Logout : ProfileUIIntent()
    object SwitchToRegistration : ProfileUIIntent()
    object SwitchToLogin : ProfileUIIntent()
    object CheckLoginStatus : ProfileUIIntent()

    data class AddProfileEntity(val profile: ProfileUI) : ProfileUIIntent()
    data class DeleteProfileEntity(val profile: ProfileUI) : ProfileUIIntent()
    data class Login(val profile: ProfileUI) : ProfileUIIntent()
    data class UpdateLogin(val login: String) : ProfileUIIntent()
    data class UpdatePassword(val password: String) : ProfileUIIntent()
    data class UpdateLoginError(val error: String?) : ProfileUIIntent()
    data class UpdateRegLogin(val regLogin: String) : ProfileUIIntent()
    data class UpdateRegPassword(val regPassword: String) : ProfileUIIntent()
    data class UpdateRegBDay(val regBday: String) : ProfileUIIntent()

}
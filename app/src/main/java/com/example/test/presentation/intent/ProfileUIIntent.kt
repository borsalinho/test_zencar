package com.example.test.presentation.intent

import com.example.test.presentation.model.ProfileUI

sealed class ProfileUIIntent {
    object LoadProfilesUI : ProfileUIIntent()
    object Logout : ProfileUIIntent()
    object CheckLoginStatus : ProfileUIIntent()
    data class AddProfileEntity(val profile: ProfileUI) : ProfileUIIntent()
    data class DeleteProfileEntity(val profile: ProfileUI) : ProfileUIIntent()
    data class Login(val profile: ProfileUI) : ProfileUIIntent()
}
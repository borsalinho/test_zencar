package com.example.test.presentation.state

import com.example.test.presentation.model.ProfileUI

data class ProfileUIState (

    val profiles: List<ProfileUI> = emptyList(),
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val loginError: String? = null,
    var isRegistrationScreen: Boolean = false,

    var login: String = "",
    var password: String = ""
)
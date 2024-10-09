package com.example.test.presentation.mapper

import com.example.test.domain.model.Profile
import com.example.test.presentation.model.ProfileUI

fun Profile.toProfileUI(): ProfileUI {
    return ProfileUI(
        id = this.id,
        userName = this.userName,
        userPassword = this.userPassword,
        userBDay = this.userBDay
    )
}
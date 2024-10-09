package com.example.test.presentation.mapper

import com.example.test.domain.model.Profile
import com.example.test.presentation.model.ProfileUI

fun ProfileUI.toProfile(): Profile {
    return Profile(
        id = this.id,
        userName = this.userName,
        userPassword = this.userPassword,
        userBDay = this.userBDay
    )
}
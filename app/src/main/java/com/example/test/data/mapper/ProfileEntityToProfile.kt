package com.example.test.data.mapper

import com.example.test.data.storage.ProfileEntity
import com.example.test.domain.model.Profile

fun ProfileEntity.toProfile(): Profile {
    return Profile(
        id = this.id,
        userName = this.userName,
        userPassword = this.userPassword,
        userBDay = this.userBDay
    )
}
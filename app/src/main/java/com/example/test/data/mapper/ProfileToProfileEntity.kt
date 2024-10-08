package com.example.test.data.mapper

import com.example.test.data.storage.ProfileEntity
import com.example.test.domain.model.Profile

fun Profile.toEntity(): ProfileEntity {
    return ProfileEntity(
        id = this.id,
        userName = this.userName,
        userPassword = this.userPassword,
        userBDay = this.userBDay
    )
}
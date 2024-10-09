package com.example.test.domain

import com.example.test.domain.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun insertProfile(profile: Profile)
    suspend fun deleteProfile(profile: Profile)
    suspend fun checkProfile(profile: Profile): Boolean
    suspend fun isLoggedIn(): Boolean
    suspend fun setLoggedIn(isLoggedIn: Boolean)
    fun getAllProfiles(): Flow<List<Profile>>
    suspend fun checkIfUserExists(profile: Profile): Int
}
package com.example.test.data

import android.util.Log
import com.example.test.data.mapper.toEntity
import com.example.test.data.mapper.toProfile
import com.example.test.data.profilepreferences.ProfilePreferences
import com.example.test.data.storage.ProfileDao
import com.example.test.domain.ProfileRepository
import com.example.test.domain.model.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProfileRepositoryImpl(
    private val dao: ProfileDao,
    private val profilePreferences: ProfilePreferences
) : ProfileRepository {

    override suspend fun insertProfile(profile: Profile) = dao.insertProfile(profile.toEntity())

    override suspend fun deleteProfile(profile: Profile) = dao.deleteProfile(profile.toEntity())

    override suspend fun checkProfile(profile: Profile): Boolean {
        val userName = profile.toEntity().userName
        val userPassword = profile.toEntity().userPassword
        return dao.checkProfile(userName, userPassword)
    }

    override suspend fun isLoggedIn(): Boolean {
        return profilePreferences.isLoggedIn
    }

    override suspend fun setLoggedIn(isLoggedIn: Boolean) {
        profilePreferences.isLoggedIn = isLoggedIn
    }

    override fun getAllProfiles(): Flow<List<Profile>> = dao.getAllProfiles().map { it ->
        it.map {
            it.toProfile()
        }
    }

    override suspend fun checkIfUserExists(profile: Profile): Int {
        val str = profile.toEntity().userName
        Log.d("MyTag", "хапрашиваю] = " + str)
        val res = dao.checkIfUserExists(str)
        Log.d("MyTag", "res = " + res)
        return res
    }
}
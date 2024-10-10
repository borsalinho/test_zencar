package com.example.test.data


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

    override suspend fun insertProfile(profile: Profile) =
        dao.insertProfile(profile.toEntity())

    override suspend fun deleteProfile(profile: Profile) =
        dao.deleteProfile(profile.toEntity())

    override suspend fun checkProfile(profile: Profile): Boolean =
        dao.checkProfile(profile.toEntity().userName)

    override suspend fun isLoggedIn(): Boolean =
        profilePreferences.isLoggedIn

    override suspend fun setLoggedIn(isLoggedIn: Boolean) {
        profilePreferences.isLoggedIn = isLoggedIn
    }

    override suspend fun setLoggedInUserId(userId: Int) {
        profilePreferences.loggedInUserId = userId
    }

    override suspend fun getLoggedInUserId(): Int {
        return profilePreferences.loggedInUserId
    }

    override fun getAllProfiles(): Flow<List<Profile>> = dao.getAllProfiles().map { it ->
            it.map {
                it.toProfile()
            }
        }

    override suspend fun checkIfUserExists(profile: Profile): Int =
        dao.checkIfUserExists(profile.toEntity().userName)

    override suspend fun getProfie(profile: Profile): Profile =
        dao.getProfile(profile.toEntity().userName).toProfile()

}
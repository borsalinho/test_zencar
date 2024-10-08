package com.example.test.data

import com.example.test.data.mapper.toEntity
import com.example.test.data.mapper.toProfile
import com.example.test.data.storage.ProfileDao
import com.example.test.domain.ProfileRepository
import com.example.test.domain.model.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProfileRepositoryImpl(private val dao: ProfileDao) : ProfileRepository {
    override suspend fun insertProfile(profile: Profile) = dao.insertProfile(profile.toEntity())

    override suspend fun deleteProfile(profile: Profile) = dao.deleteProfile(profile.toEntity())

    override suspend fun checkProfile(profile: Profile): Boolean {
        val userName = profile.toEntity().userName
        val userPassword = profile.toEntity().userPassword
        return dao.checkProfile(userName, userPassword)
    }

    override fun getAllProfiles(): Flow<List<Profile>> = dao.getAllProfiles().map { it ->
        it.map {
            it.toProfile()
        }
    }
}
package com.example.test.data.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profileEntity: ProfileEntity)

    @Delete
    suspend fun deleteProfile(profileEntity: ProfileEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM profile_table WHERE userName = :userName AND userPassword = :userPassword)")
    suspend fun checkProfile(userName: String, userPassword: String): Boolean

    @Query("select * from profile_table")
    fun getAllProfiles() : Flow<List<ProfileEntity>>

    @Query("SELECT COUNT(*) FROM profile_table WHERE userName = :userName")
    suspend fun checkIfUserExists(userName: String): Int
}
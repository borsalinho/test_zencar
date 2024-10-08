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

    @Query("select * from profile_table")
    fun getAllProfiles() : Flow<List<ProfileEntity>>
}
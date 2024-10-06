package com.example.test.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
data class ProfileEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name : String
)
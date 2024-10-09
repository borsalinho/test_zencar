package com.example.test.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test.data.ProfileRepositoryImpl

class ProfileUIViewModelFactory(
    private val profileRepository: ProfileRepositoryImpl
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileUIViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileUIViewModel(profileRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
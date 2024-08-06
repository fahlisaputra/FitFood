package com.example.fitfoood.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitfoood.data.pref.ProfileModel
import com.example.fitfoood.data.pref.TokenModel
import com.example.fitfoood.data.repository.UserRepository
import com.example.fitfoood.data.pref.UserModel
import com.example.fitfoood.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository, private val authRepository: AuthRepository) : ViewModel() {
    fun saveUser(user: UserModel) {
        viewModelScope.launch {
            userRepository.saveUser(user)
        }
    }

    fun saveProfile(profile: ProfileModel) {
        viewModelScope.launch {
            userRepository.saveProfile(profile)
        }
    }

    suspend fun saveToken(token: TokenModel) {
        userRepository.saveToken(token)
    }
    fun signIn(email: String, password: String) =
        authRepository.signIn(email, password)

    fun getUser() = authRepository.getUser()
}
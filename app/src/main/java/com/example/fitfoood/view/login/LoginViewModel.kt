package com.example.fitfoood.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitfoood.data.pref.TokenModel
import com.example.fitfoood.data.repository.UserRepository
import com.example.fitfoood.data.pref.UserModel
import com.example.fitfoood.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository, private val authRepository: AuthRepository) : ViewModel() {
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            userRepository.saveSession(user)
        }
    }

    suspend fun saveToken(token: TokenModel) {
        userRepository.saveToken(token)
    }
    fun signIn(email: String, password: String) =
        authRepository.signIn(email, password)

    fun getUser() = authRepository.getUser()
}
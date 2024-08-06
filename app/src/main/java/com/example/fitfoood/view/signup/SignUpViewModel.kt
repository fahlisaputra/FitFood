package com.example.fitfoood.view.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitfoood.data.pref.UserModel
import com.example.fitfoood.data.repository.AuthRepository
import com.example.fitfoood.data.repository.UserRepository
import com.example.fitfoood.data.request.SignUpRequestData
import kotlinx.coroutines.launch

class SignUpViewModel(private val userRepository: UserRepository, private val authRepository: AuthRepository) : ViewModel() {
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            userRepository.saveSession(user)
        }
    }
    fun signUp(payload: SignUpRequestData) = authRepository.signUp(payload)
}

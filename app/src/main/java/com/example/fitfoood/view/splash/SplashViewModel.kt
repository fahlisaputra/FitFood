package com.example.fitfoood.view.splash

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitfoood.data.pref.TokenModel
import com.example.fitfoood.data.repository.UserRepository
import com.example.fitfoood.data.pref.UserModel
import com.example.fitfoood.data.repository.InternetRepository
import kotlinx.coroutines.launch

class SplashViewModel(private val userRepository: UserRepository, internetRepository: InternetRepository): ViewModel(){

    val isOnline = internetRepository.isConnected.asLiveData()

    fun getSession(): LiveData<UserModel> {
        return userRepository.getSession().asLiveData()
    }

    fun getToken(): LiveData<TokenModel> {
        return userRepository.getToken().asLiveData()
    }

    fun saveToken(token: TokenModel) {
        viewModelScope.launch {
            userRepository.saveToken(token)
        }
    }

    fun signOut() {
        viewModelScope.launch {
            userRepository.logout()
        }
    }

    fun refreshToken() = userRepository.refreshToken()

}
package com.example.fitfoood.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fitfoood.data.ApiResponse
import com.example.fitfoood.data.api.enqueueLiveData
import com.example.fitfoood.data.response.SignInResponse
import com.example.fitfoood.data.response.UserResponse
import com.example.fitfoood.source.ApiService

/**
 * Auth Repository
 *
 * @property apiService API Service
 * @constructor Create Auth Repository instance
 */
class AuthRepository(
    private val apiService: ApiService,
) {

    /**
     * Sign in user
     *
     * @param email Email address
     * @param password Password
     * @return Sign In Response
     */
    fun signIn(email: String, password: String): LiveData<ApiResponse<SignInResponse>> {
        val result = MutableLiveData<ApiResponse<SignInResponse>>()
        val client = apiService.signIn(email, password)
        client.enqueueLiveData(result)
        return result
    }

    /**
     * Get user data
     *
     * @return User Response
     */
    fun getUser(): LiveData<ApiResponse<UserResponse>> {
        val result = MutableLiveData<ApiResponse<UserResponse>>()
        val client = apiService.getUser()
        client.enqueueLiveData(result)
        return result
    }

//    fun userRegister(username: String, email: String, password: String, dateOfBirth: String,gender:String) = liveData {
//        emit(ApiResponse.Loading)
//        try {
//            val response = apiService.register(RegisterRequest(username, email, password, dateOfBirth,gender))
//            if (response.isSuccessful) {
//                emit(ApiResponse.Success(response.body()))
//            } else {
//                emit(Error(response.message()))
//            }
//        } catch (e: Exception) {
//            emit(Error(e.message))
//        }
//    }

//    fun updateUser( idUser:String,userUpdate:UserUpdate): LiveData<ApiResponse<UpdatUserResponse>> {
//        val result = MutableLiveData<ApiResponse<UpdatUserResponse>>()
//        result.value = ApiResponse.Loading
//
//        val client = apiService.updateUser( idUser, userUpdate)
//        client.enqueue(object : Callback<UpdatUserResponse> {
//            override fun onResponse(
//                call: Call<UpdatUserResponse>,
//                response: Response<UpdatUserResponse>
//            ) {
//                if (response.isSuccessful) {
//                    result.value = ApiResponse.Success(response.body()!!)
//                } else {
//                    result.value = ApiResponse.Error(response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<UpdatUserResponse>, t: Throwable) {
//                result.value = ApiResponse.Error(t.message.toString())
//            }
//        })
//        return result
//    }
}

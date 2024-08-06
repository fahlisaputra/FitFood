package com.example.fitfoood.data.repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fitfoood.data.ApiResponse
import com.example.fitfoood.data.pref.TokenModel
import com.example.fitfoood.data.pref.UserModel
import com.example.fitfoood.data.pref.UserPreference
import com.example.fitfoood.data.response.SignInResponse
import com.example.fitfoood.source.ApiService
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    suspend fun saveToken(token: TokenModel) {
        userPreference.saveToken(token)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    fun getUser(): Flow<UserModel> {
        return userPreference.getUser()
    }

    fun getToken(): Flow<TokenModel> {
        return userPreference.getToken()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    fun refreshToken(): LiveData<ApiResponse<SignInResponse>> {
        val result = MutableLiveData<ApiResponse<SignInResponse>>()
        result.value = ApiResponse.Loading

        val client = apiService.refreshToken()
        client.enqueue(object : Callback<SignInResponse> {
            override fun onResponse(
                call: Call<SignInResponse>,
                response: Response<SignInResponse>
            ) {
                if (response.isSuccessful) {
                    result.value = ApiResponse.Success(response.body()!!)
                } else {
                    result.value = ApiResponse.Error(response.message())
                }
            }

            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                result.value = ApiResponse.Error(t.message.toString())
            }
        })
        return result
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(userPreference: UserPreference, apiService: ApiService): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, apiService)
            }.also { instance = it }
    }
}

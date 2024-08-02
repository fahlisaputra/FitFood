package com.example.fitfoood.data.api

import androidx.lifecycle.MutableLiveData
import com.example.fitfoood.data.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Enqueue live data
 * This extension function is used to enqueue live data.
 *
 * @param liveData Mutable live data
 * @param T Type
 * @receiver Call
 * @return Unit
 * @see ApiResponse
 */
fun <T> Call<T>.enqueueLiveData(liveData: MutableLiveData<ApiResponse<T>>) {
    liveData.value = ApiResponse.Loading
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                response.body()?.let { liveData.value = ApiResponse.Success(it) }
            } else {
                liveData.value = ApiResponse.Error(response.message())
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            liveData.value = ApiResponse.Error(t.message.toString())
        }
    })
}
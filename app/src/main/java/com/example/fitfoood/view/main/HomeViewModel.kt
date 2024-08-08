package com.example.fitfoood.view.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitfoood.data.ApiResponse
import com.example.fitfoood.data.pref.BMIModel
import com.example.fitfoood.data.pref.ProfileModel
import com.example.fitfoood.data.pref.UserModel
import com.example.fitfoood.data.repository.ArticleRepository
import com.example.fitfoood.data.repository.BMIRepository
import com.example.fitfoood.data.repository.UserRepository
import com.example.fitfoood.data.response.ArticleResponse
import com.example.fitfoood.data.response.ArtikelResponseItem
import com.example.fitfoood.data.response.BMI
import com.example.fitfoood.data.response.FoodBMIResponseItem
import com.example.fitfoood.data.response.GetBMIResponse
import com.example.fitfoood.data.response.PostBMIResponse
import com.example.fitfoood.data.response.WoBMIResponseItem
import kotlinx.coroutines.launch

class HomeViewModel(private val articleRepository: ArticleRepository, private val userRepository: UserRepository, private val bmiRepository: BMIRepository) : ViewModel() {

    fun getUser(): LiveData<UserModel> {
        return userRepository.getUser().asLiveData()
    }

    fun getProfile(): LiveData<ProfileModel> {
        return userRepository.getProfile().asLiveData()
    }

    fun getArticles(): LiveData<ApiResponse<ArticleResponse>> {
        return articleRepository.getArticles()
    }

//    fun postBMI(token: String, idhealth: String, bmi: BMI): LiveData<ApiResponse<PostBMIResponse>> {
//        return repository.postBMI(token, idhealth, bmi)
//    }

//    fun getFoodRec(token: String): LiveData<ApiResponse<List<FoodBMIResponseItem>>> {
//        return repository.getFoodRec(token)
//    }
//    fun getWoRec(token: String): LiveData<ApiResponse<List<WoBMIResponseItem>>> {
//        return repository.getExerciseRec(token)
//    }
}

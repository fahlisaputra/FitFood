package com.example.fitfoood.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.fitfoood.MainActivity
import com.example.fitfoood.data.ApiResponse
import com.example.fitfoood.data.pref.TokenModel
import com.example.fitfoood.databinding.ActivitySplashBinding
import com.example.fitfoood.view.ViewModelFactory
import com.example.fitfoood.view.login.LoginActivity
import com.example.fitfoood.view.welcome.WelcomeScreenActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel

    private fun <T> LiveData<T>.observeOnce(observer: (T) -> Unit) {
        observeForever(object: Observer<T> {
            override fun onChanged(value: T) {
                removeObserver(this)
                observer(value)
            }
        })
    }

    private fun <T> LiveData<T>.observeOnce(owner: LifecycleOwner, observer: (T) -> Unit) {
        observe(owner, object: Observer<T> {
            override fun onChanged(value: T) {
                removeObserver(this)
                observer(value)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelFactory.getInstance(this).create(SplashViewModel::class.java)
        val context = this
        Handler(mainLooper).postDelayed({
            viewModel.getToken().observeOnce(this) {user ->
                if (user.accessToken != "") {
                    viewModel.isOnline.observeOnce(this) { isOnline ->
                        if (isOnline) {
                            binding.progressBar.visibility = View.VISIBLE
                            viewModel.refreshToken().observe(this) {result ->
                                when(result) {
                                    is ApiResponse.Error -> {
                                        viewModel.signOut()
                                        startActivity(Intent(context, LoginActivity::class.java))
                                        finish()
                                    }
                                    ApiResponse.Loading -> {}
                                    is ApiResponse.Success -> {
                                        if (result.data == null) {
                                            viewModel.signOut()
                                            startActivity(Intent(context, LoginActivity::class.java))
                                            finish()
                                        }else{
                                            val tokens = result.data.data
                                            viewModel.saveToken(
                                                TokenModel(
                                                    tokens?.accessToken,
                                                    tokens?.refreshToken
                                                )
                                            )
                                            startActivity(Intent(context, MainActivity::class.java))
                                            finish()
                                        }

                                    }
                                }
                            }
                        } else {
                            startActivity(Intent(context, MainActivity::class.java))
                            finish()
                        }
                    }
                } else {
                    startActivity(Intent(context, WelcomeScreenActivity::class.java))
                    finish()
                }
            }
        }, SPLASH_DELAY)
    }

    companion object {
        private const val SPLASH_DELAY: Long = 2000
    }
}
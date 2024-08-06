package com.example.fitfoood.view.login


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.fitfoood.MainActivity
import com.example.fitfoood.data.ApiResponse
import com.example.fitfoood.data.pref.ProfileModel
import com.example.fitfoood.data.pref.TokenModel
import com.example.fitfoood.data.pref.UserModel
import com.example.fitfoood.databinding.ActivityLoginBinding
import com.example.fitfoood.view.ViewModelFactory
import com.example.fitfoood.view.forgotpass.ForgotPassActivity
import com.example.fitfoood.view.signup.SignUpActivity
import kotlinx.coroutines.runBlocking

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = ViewModelFactory.getInstance(this).create(LoginViewModel::class.java)
        setContentView(binding.root)

//        (binding.forgotPassword.setOnClickListener {
//            startActivity(Intent(this, ForgotPassActivity::class.java))
//        })

        setupView()
        setupAction()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.emailEditText.setText("saputra@fahli.net")
        binding.passwordEditText.setText("password")
        binding.SignUpTextView.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.signIn(email, password).observe(this) {result ->
                when(result) {
                    is ApiResponse.Error -> {
                        Toast.makeText(this, "Password atau email yang Anda masukkan salah", Toast.LENGTH_SHORT).show()
                    }
                    ApiResponse.Loading -> {}
                    is ApiResponse.Success -> {
                        if (result.data == null) {
                            AlertDialog.Builder(this).apply {
                                setTitle("Oops!")
                                setMessage("Email atau password yang Anda masukkan salah.")
                                setPositiveButton("OK") { _, _ -> }
                                create()
                                show()
                            }
                        }else{
                            val tokens = result.data.data

                            val tokenModel = TokenModel(
                                tokens?.accessToken,
                                tokens?.refreshToken
                            )

                            runBlocking {
                                viewModel.saveToken(
                                    tokenModel
                                )

                                getUser()
                            }

                            return@observe
                        }

                    }
                }
            }
        }
    }

    private fun getUser() {
        viewModel.getUser().observe(this) {result ->
            when(result) {
                is ApiResponse.Error -> {
                    Toast.makeText(this, "Password atau email yang Anda masukkan salah", Toast.LENGTH_SHORT).show()
                }
                ApiResponse.Loading -> {}
                is ApiResponse.Success -> {
                    if (result.data == null) {
                        AlertDialog.Builder(this).apply {
                            setTitle("Oops!")
                            setMessage("Email atau password yang Anda masukkan salah.")
                            setPositiveButton("OK") { _, _ -> }
                            create()
                            show()
                        }
                    }else{
                        val user = result.data.data?.user
                        viewModel.saveUser(
                            UserModel(
                                user?.name,
                                user?.email,
                                user?.id
                            )
                        )

                        viewModel.saveProfile(
                            ProfileModel(
                                user?.profile?.birthDate,
                                user?.profile?.gender,
                                user?.profile?.height,
                                user?.profile?.weight,
                                user?.profile?.bmiIndex,
                                user?.profile?.bmiLabel
                            )
                        )

                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                        return@observe
                    }
                }
            }
        }
    }
}

package com.gdscug.mooflix.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.gdscug.mooflix.R
import com.gdscug.mooflix.data.model.User
import com.gdscug.mooflix.databinding.ActivityLoginBinding
import com.gdscug.mooflix.ui.home.HomeActivity
import com.gdscug.mooflix.ui.register.RegisterActivity
import com.gdscug.mooflix.utils.ViewModelFactory
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupAction()
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(this)
        loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        loginViewModel.getUser().observe(this) { user ->
            this.user = user
        }
    }

    private fun setupData() {
        binding.apply {
            val email = edtEmail.text.toString()
            val pass = edtPassword.text.toString()

            when {
                email.isEmpty() -> {
                    tfEmail.error = getString(R.string.cant_empty)
                }
                pass.isEmpty() -> {
                    tfPassword.error = getString(R.string.cant_empty)
                }
                email != user.email -> {
                    tfEmail.error = getString(R.string.email_invalid)
                }
                pass != user.password -> {
                    tfPassword.error = getString(R.string.pass_invalid)
                }
                else -> {
                    loginViewModel.login()
                    Toast.makeText(this@LoginActivity, R.string.login_success, Toast.LENGTH_SHORT).show()
                    moveToHome()
                }
            }
        }
    }

    private fun setupAction() {
        binding.apply {
            btnLogin.setOnClickListener { setupData() }
            tvRegister.setOnClickListener { moveToRegister() }
        }
    }

    private fun moveToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun moveToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }
}
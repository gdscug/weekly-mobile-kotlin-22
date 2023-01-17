package com.gdscug.mooflix.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.gdscug.mooflix.R
import com.gdscug.mooflix.data.model.User
import com.gdscug.mooflix.databinding.ActivityRegisterBinding
import com.gdscug.mooflix.ui.login.LoginActivity
import com.gdscug.mooflix.utils.ViewModelFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupAction()
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(this)
        registerViewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]
    }

    private fun setupData() {
        binding.apply {
            val name = edtUsername.text.toString()
            val email = edtEmail.text.toString()
            val pass = edtPassword.text.toString()

            when {
                name.isEmpty() -> {
                    tfUsername.error = getString(R.string.cant_empty)
                }

                email.isEmpty() -> {
                    tfEmail.error = getString(R.string.cant_empty)
                }
                pass.isEmpty() -> {
                    tfPassword.error = getString(R.string.cant_empty)
                }
                else -> {
                    registerViewModel.saveUser(User(name, email, pass, false))
                    Toast.makeText(this@RegisterActivity, R.string.register_success, Toast.LENGTH_SHORT).show()
                    moveActivity()
                }
            }
        }
    }

    private fun setupAction() {
        binding.apply {
            btnRegister.setOnClickListener { setupData() }
            tvLogin.setOnClickListener { moveActivity() }
        }
    }

    private fun moveActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
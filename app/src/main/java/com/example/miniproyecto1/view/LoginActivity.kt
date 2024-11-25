package com.example.miniproyecto1.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.miniproyecto1.R
import com.example.miniproyecto1.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailField: TextInputEditText = findViewById(R.id.etEmail)
        val passwordField: TextInputEditText = findViewById(R.id.etPassword)
        val emailLayout: TextInputLayout = findViewById(R.id.tilEmail)
        val passwordLayout: TextInputLayout = findViewById(R.id.tilPassword)
        val loginButton: Button = findViewById(R.id.btnLogin)
        val registerTextView: TextView = findViewById(R.id.tvRegister)

        validateFields(emailField, passwordField, loginButton, registerTextView)

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateFields(emailField, passwordField, loginButton, registerTextView)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        emailField.addTextChangedListener(textWatcher)
        passwordField.addTextChangedListener(textWatcher)

        // Email field validation (check for valid email format)
        emailField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = s.toString()
                emailLayout.error = when {
                    email.isEmpty() -> "Por favor ingrese un correo"
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Correo no válido"
                    else -> null
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Password field validation (password should be exactly 6 characters)
        passwordField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()
                passwordLayout.error = when {
                    password.length < 6 -> "La contraseña debe tener exactamente 6 caracteres"
                    password.length > 6 -> "La contraseña debe tener exactamente 6 caracteres"
                    else -> null
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Button click to handle login process
        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            // Validate empty fields and password length
            if (email.isEmpty() || password.isEmpty()) {
                if (email.isEmpty()) {
                    emailLayout.error = "Por favor ingrese un correo"
                } else {
                    emailLayout.error = null
                }

                if (password.isEmpty()) {
                    passwordLayout.error = "Por favor ingrese una contraseña"
                } else {
                    passwordLayout.error = null
                }

                Toast.makeText(this, "Por favor, complete los campos", Toast.LENGTH_SHORT).show()
            } else if (password.length != 6) {
                passwordLayout.error = "La contraseña debe tener exactamente 6 caracteres"
                Toast.makeText(this, "La contraseña debe tener exactamente 6 caracteres", Toast.LENGTH_SHORT).show()
            } else {
                // Clear errors when everything is valid
                emailLayout.error = null
                passwordLayout.error = null

                loginViewModel.login(email, password) { isSuccess, message ->
                    if (isSuccess) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, message ?: "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun validateFields(
        emailField: TextInputEditText,
        passwordField: TextInputEditText,
        loginButton: Button,
        registerTextView: TextView
    ) {
        val email = emailField.text.toString().trim()
        val password = passwordField.text.toString().trim()
        val isValid = email.isNotBlank() && password.isNotBlank()

        loginButton.isEnabled = isValid
        registerTextView.isEnabled = isValid

        // Change the color of the Register TextView when fields are valid
        if (isValid) {
            registerTextView.setTextColor(getResources().getColor(R.color.white)) // Set to white color when enabled
        } else {
            registerTextView.setTextColor(getResources().getColor(R.color.gray)) // Set to gray color when not enabled
        }
    }
}










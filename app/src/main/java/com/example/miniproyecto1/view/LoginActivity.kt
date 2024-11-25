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

        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

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

        // Agregar funcionalidad de registro
        registerTextView.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos para registrarse", Toast.LENGTH_SHORT).show()
            } else if (password.length != 6) {
                Toast.makeText(this, "La contraseña debe tener exactamente 6 caracteres", Toast.LENGTH_SHORT).show()
            } else {
                loginViewModel.register(email, password) { isSuccess, message ->
                    if (isSuccess) {
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, message ?: "Error en el registro", Toast.LENGTH_SHORT).show()
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

        if (isValid) {
            registerTextView.setTextColor(resources.getColor(R.color.white))
        } else {
            registerTextView.setTextColor(resources.getColor(R.color.gray))
        }
    }
}











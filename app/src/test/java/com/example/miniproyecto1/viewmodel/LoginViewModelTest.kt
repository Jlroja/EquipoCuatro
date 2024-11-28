package com.example.miniproyecto1.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.miniproyecto1.repository.LoginRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*

class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var loginRepository: LoginRepository
    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        loginRepository = mock()
        loginViewModel = LoginViewModel(loginRepository)
    }

    @Test
    fun login() {
        // Arrange
        val email = "test@example.com"
        val password = "password123"
        val successMessage = "Login successful"

        // Mock the repository's login function to invoke the callback with success
        doAnswer { invocation ->
            val callback = invocation.getArgument<((Boolean, String) -> Unit)>(2)
            callback.invoke(true, successMessage)
        }.`when`(loginRepository).login(eq(email), eq(password), any())

        // Act
        var loginResult: Pair<Boolean, String>? = null
        loginViewModel.login(email, password) { isSuccess, message ->
            loginResult = Pair(isSuccess, message)
        }

        // Assert
        verify(loginRepository).login(eq(email), eq(password), any())
        Assert.assertEquals(Pair(true, successMessage), loginResult)
    }

    @Test
    fun register() {
        // Arrange
        val email = "newuser@example.com"
        val password = "newpassword"
        val errorMessage = "Registration failed"

        // Mock the repository's register function to invoke the callback with failure
        doAnswer { invocation ->
            val callback = invocation.getArgument<((Boolean, String) -> Unit)>(2)
            callback.invoke(false, errorMessage)
        }.`when`(loginRepository).register(eq(email), eq(password), any())

        // Act
        var registerResult: Pair<Boolean, String>? = null
        loginViewModel.register(email, password) { isSuccess, message ->
            registerResult = Pair(isSuccess, message)
        }

        // Assert
        verify(loginRepository).register(eq(email), eq(password), any())
        Assert.assertEquals(Pair(false, errorMessage), registerResult)
    }
}
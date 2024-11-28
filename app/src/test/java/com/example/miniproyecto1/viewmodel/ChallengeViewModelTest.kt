package com.example.miniproyecto1.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.miniproyecto1.model.Challenge
import com.example.miniproyecto1.repository.ChallengeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class ChallengeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var challengeRepository: ChallengeRepository
    private lateinit var challengeViewModel: ChallengeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        challengeRepository = mock()
        challengeViewModel = ChallengeViewModel(mock(), challengeRepository)
    }

    @Test
    fun `saveChallenge deberia llamar el repositorio y actualizar el estado`() = runTest {
        // Arrange
        val challenge = Challenge(id = "1", description = "Test Challenge")

        // Act
        challengeViewModel.saveChallenge(challenge)

        // Assert
        verify(challengeRepository).saveChallenge(challenge)
        assertEquals(false, challengeViewModel.progresState.value)
    }

    @Test
    fun `updateChallenge deberia llamar el repositorio y actualizar el estado`() = runTest {
        // Arrange
        val challenge = Challenge(id = "2", description = "Updated Challenge")

        // Act
        challengeViewModel.updateChallenge(challenge)

        // Assert
        verify(challengeRepository).updateChallenge(challenge)
        assertEquals(false, challengeViewModel.progresState.value)
    }

    @Test
    fun `deleteChallenge deberia llamar el repositorio y actualizar el estado`() = runTest {
        // Arrange
        val challenge = Challenge(id = "3", description = "Delete Challenge")

        // Act
        challengeViewModel.deleteChallenge(challenge)

        // Assert
        verify(challengeRepository).deleteChallenge(challenge)
        assertEquals(false, challengeViewModel.progresState.value)
    }
}

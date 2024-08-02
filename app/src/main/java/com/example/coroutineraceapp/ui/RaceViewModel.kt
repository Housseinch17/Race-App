package com.example.coroutineraceapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutineraceapp.R
import com.example.coroutineraceapp.data.DataSource
import com.example.coroutineraceapp.data.Player
import com.example.coroutineraceapp.data.RaceUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.coroutineraceapp.data.Player.PlayerOne
import com.example.coroutineraceapp.data.Player.PlayerTwo

@HiltViewModel
class RaceViewModel @Inject constructor() : ViewModel() {
    private val _raceUiState = MutableStateFlow(RaceUiState())
    val raceUiState = _raceUiState.asStateFlow()

    private var playerOneJob: Job? = null
    private var playerTwoJob: Job? = null


    private fun cancelAllJobs() {
        playerOneJob?.cancel()
        playerTwoJob?.cancel()
    }

    fun reset() {
        _raceUiState.value = RaceUiState()
        cancelAllJobs()
    }

    suspend fun startRace() {
        viewModelScope.launch {
            val buttonText = _raceUiState.value.buttonText
            val start = R.string.start
            val pause = R.string.pause
            when (buttonText) {
                start -> {
                    _raceUiState.update {
                        it.copy(buttonText = pause)
                    }
                    playerOneJob = launch { updatePlayer(_raceUiState.value.playerOne) }
                    playerTwoJob = launch { updatePlayer(_raceUiState.value.playerTwo) }
                }

                pause -> {
                    _raceUiState.update {
                        it.copy(buttonText = start)
                    }
                    cancelAllJobs()
                }
            }
        }
    }

    private suspend fun updatePlayer(player: Player) {
        var playerProgression = player.playerCurrentProgress
        val playerIncrement = player.playerIncrement
        var playerPercentage: Int
        while (playerProgression < DataSource.maxPlayerProgress) {
            playerProgression += playerIncrement
            playerPercentage = playerProgression


            val updatedPlayer = when (player) {
                is PlayerOne -> player.copy(
                    playerCurrentProgress = playerProgression,
                    percentage = playerPercentage
                )

                is PlayerTwo -> player.copy(
                    playerCurrentProgress = playerProgression,
                    percentage = playerPercentage
                )
            }

            _raceUiState.update {
                it.copy(
                    playerOne = if (player is PlayerOne) updatedPlayer else it.playerOne,
                    playerTwo = if (player is PlayerTwo) updatedPlayer else it.playerTwo
                )
            }
            delay(500)
        }
    }
}








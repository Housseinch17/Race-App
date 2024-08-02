package com.example.coroutineraceapp.data

import com.example.coroutineraceapp.R
import com.example.coroutineraceapp.data.Player.PlayerOne
import com.example.coroutineraceapp.data.Player.PlayerTwo

data class RaceUiState(
    val playerOne: Player = PlayerOne("Player 1", 0, 1, 0),
    val playerTwo: Player = PlayerTwo("Player 2",0,2,0),
    val buttonText: Int = R.string.start,
)
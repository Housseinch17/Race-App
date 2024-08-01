package com.example.coroutineraceapp.data

import com.example.coroutineraceapp.R

data class RaceUiState(
    val playerOne: Player = Player("Player 1",0,1,0),
    val playerTwo: Player = Player("Player 2",0,2,0),
    val buttonText: Int = R.string.start,
)
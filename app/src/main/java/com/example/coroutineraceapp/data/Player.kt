package com.example.coroutineraceapp.data

sealed class Player(
    open val playerName: String,
    open var playerCurrentProgress: Int,
    open val playerIncrement: Int,
    open var percentage: Int,
) {
    data class PlayerOne(
        override val playerName: String,
        override var playerCurrentProgress: Int,
        override val playerIncrement: Int,
        override var percentage: Int
    ) : Player(playerName, playerCurrentProgress, playerIncrement, percentage)

    data class PlayerTwo(
        override val playerName: String,
        override var playerCurrentProgress: Int,
        override val playerIncrement: Int,
        override var percentage: Int
    ) : Player(playerName, playerCurrentProgress, playerIncrement, percentage)
}

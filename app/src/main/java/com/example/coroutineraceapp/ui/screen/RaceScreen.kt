package com.example.coroutineraceapp.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coroutineraceapp.R
import com.example.coroutineraceapp.data.Player
import com.example.coroutineraceapp.ui.RaceViewModel
import kotlinx.coroutines.runBlocking


@Composable
fun RaceScreen(modifier: Modifier,raceViewModel: RaceViewModel){
    val raceUiState by raceViewModel.raceUiState.collectAsStateWithLifecycle()
    MyScreen(
        modifier = modifier,
        playerOne = raceUiState.playerOne,
        playerTwo = raceUiState.playerTwo,
        raceButtonText = raceUiState.buttonText,
        raceButtonClick = {
            runBlocking {
                raceViewModel.startRace()
            }
        },
        resetButtonText = R.string.reset,
        resetButtonClick = raceViewModel::reset
    )
}

@Composable
fun MyScreen(
    modifier: Modifier,
    playerOne: Player,
    playerTwo: Player,
    raceButtonText: Int,
    raceButtonClick: () -> Unit,
    resetButtonText: Int,
    resetButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.run_race),
            modifier = Modifier,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(R.drawable.race),
            contentDescription = stringResource(id = R.string.race),
            modifier = Modifier.padding(
                top = 10.dp
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        PlayerProgressBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            playerName = playerOne.playerName,
            playerProgress = playerOne.playerCurrentProgress.toFloat() / 100,
            percentage = playerOne.percentage
        )
        Spacer(modifier = Modifier.height(10.dp))
        PlayerProgressBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            playerName = playerTwo.playerName,
            playerProgress = playerTwo.playerCurrentProgress.toFloat() / 100,
            percentage = playerTwo.percentage
        )
        Spacer(modifier = Modifier.height(20.dp))
        Buttons(
            modifier = Modifier.fillMaxWidth(),
            onClick = raceButtonClick,
            text = stringResource(id = raceButtonText),
            buttonColors = ButtonDefaults.buttonColors(
                Color.Blue
            ),
            textColor = Color.White
        )
        Spacer(modifier = Modifier.height(14.dp))
        Buttons(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            onClick = resetButtonClick,
            text = stringResource(id = resetButtonText),
            borderStroke = BorderStroke(1.dp, Color.Gray),
            buttonColors = ButtonDefaults.buttonColors(Color.White),
            textColor = Color.Black
        )


    }
}

@Composable
fun Buttons(
    modifier: Modifier,
    onClick: () -> Unit,
    text: String,
    borderStroke: BorderStroke? = null,
    buttonColors: ButtonColors,
    textColor: Color
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(25.dp),
        border = borderStroke,
        colors = buttonColors
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyMedium, color = textColor)
    }
}

@Composable
fun PlayerProgressBar(
    modifier: Modifier, playerName: String, playerProgress: Float, percentage: Int
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(text = playerName, style = MaterialTheme.typography.bodyMedium)
        ProgressPercentage(
            modifier = Modifier.fillMaxWidth(1f), progress = playerProgress, percentage = percentage
        )
    }
}


@Composable
fun ProgressPercentage(modifier: Modifier, progress: Float, percentage: Int) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
        ProgressBar(modifier = modifier, progress = progress)
        ProgressPercentage(modifier = modifier, percentage = percentage)
    }
}

@Composable
fun ProgressBar(modifier: Modifier = Modifier, progress: Float) {
    LinearProgressIndicator(
        progress = {
            progress
        }, modifier = modifier
    )
}

@Composable
fun ProgressPercentage(modifier: Modifier, percentage: Int) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "$percentage%", style = MaterialTheme.typography.bodyMedium)
        Text(text = "100%", style = MaterialTheme.typography.bodyMedium)
    }
}
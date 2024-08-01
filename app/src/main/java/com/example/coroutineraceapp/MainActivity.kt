package com.example.coroutineraceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coroutineraceapp.ui.RaceViewModel
import com.example.coroutineraceapp.ui.screen.RaceScreen
import com.example.coroutineraceapp.ui.theme.CoroutineRaceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoroutineRaceAppTheme {
                val raceViewModel: RaceViewModel = viewModel()
                RaceScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp), raceViewModel = raceViewModel
                )
            }
        }
    }
}



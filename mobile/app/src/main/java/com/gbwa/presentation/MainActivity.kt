package com.gbwa.presentation

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gbwa.presentation.home.WeatherCard
import com.gbwa.presentation.home.WeatherForecast
import com.gbwa.presentation.home.WeatherViewModel
import com.gbwa.presentation.ui.theme.DarkBlue
import com.gbwa.presentation.ui.theme.DeepBlue
import com.gbwa.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ))

        setContent {
            WeatherAppTheme {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .background(DarkBlue)) {
                    WeatherCard(state = viewModel.state, backgroundColor = DeepBlue)
                    Spacer(modifier = Modifier.height(16.dp))
                    WeatherForecast(state = viewModel.state )
                }
            }
        }
    }
}

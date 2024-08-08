@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.mytaxi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mytaxi.presentation.intent.MainScreenEvents
import com.example.mytaxi.presentation.main.MainScreen
import com.example.mytaxi.presentation.theme.MyTaxiTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.state.collectAsStateWithLifecycle()
            MyTaxiTheme {
                MainScreen(
                    currentLocation = state.currentLocation,
                    getLocation = { viewModel.reduce(MainScreenEvents.GetCurrentLocation) }
                )
            }
        }
    }
}
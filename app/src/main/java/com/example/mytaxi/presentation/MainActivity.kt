package com.example.mytaxi.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mytaxi.presentation.intent.MainScreenEvents
import com.example.mytaxi.presentation.main.MainScreen
import com.example.mytaxi.presentation.theme.MyTaxiTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalPermissionsApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.state.collectAsStateWithLifecycle()
            val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

            LaunchedEffect(permissionState.status) {
                if (permissionState.status.isGranted) {
                    viewModel.reduce(MainScreenEvents.GetCurrentLocation)
                } else {
                    permissionState.launchPermissionRequest()
                }
            }

            MyTaxiTheme {
                MainScreen(state.currentLocation)
            }
        }
    }
}
package com.example.mytaxi.presentation.intent

sealed class MainScreenEvents {
    data object GetCurrentLocation : MainScreenEvents()
}
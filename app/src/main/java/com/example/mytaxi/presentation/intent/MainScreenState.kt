package com.example.mytaxi.presentation.intent

import com.google.android.gms.maps.model.LatLng

data class MainScreenState(
    val currentLocation: LatLng = LatLng(0.0, 0.0),
    val isLoading: Boolean = false
)
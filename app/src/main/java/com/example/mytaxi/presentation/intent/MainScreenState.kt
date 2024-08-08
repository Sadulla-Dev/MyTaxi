package com.example.mytaxi.presentation.intent

import com.google.android.gms.maps.model.LatLng

data class MainScreenState(
    val currentLocation: LatLng? = null
)
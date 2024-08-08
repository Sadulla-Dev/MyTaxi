package com.example.mytaxi.domain

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getCurrentLocation(): Flow<LatLng>
    suspend fun saveLocation(location: LatLng)
}
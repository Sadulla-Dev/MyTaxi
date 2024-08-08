package com.example.mytaxi.data

import android.annotation.SuppressLint
import android.content.Context
import com.example.mytaxi.data.dao.LocationDao
import com.example.mytaxi.data.model.toEntity
import com.example.mytaxi.domain.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationDao: LocationDao,
    private val context: Context,
) : LocationRepository {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override fun getCurrentLocation(): Flow<LatLng?> = flow {
        val location = fusedLocationClient.lastLocation.await()
        emit(location?.let { LatLng(it.latitude, it.longitude) })
    }.flowOn(Dispatchers.IO)

    override suspend fun saveLocation(location: LatLng) {
        withContext(Dispatchers.IO) {
            locationDao.insertLocation(location.toEntity())
        }
    }
}

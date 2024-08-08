package com.example.mytaxi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng

@Entity(tableName = "locations")
data class LocationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val latitude: Double,
    val longitude: Double
)

fun LatLng.toEntity() = LocationEntity(
    latitude = this.latitude,
    longitude = this.longitude
)

package com.example.mytaxi.presentation.utils

import com.google.android.gms.maps.model.LatLng
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager

fun showUserLocation(
    mapView: MapView,
    pointAnnotationManager: PointAnnotationManager?,
    currentLocation: LatLng
) {
    mapView.getMapboxMap()
        .flyTo(
            CameraOptions.Builder()
                .center(Point.fromLngLat(currentLocation.longitude, currentLocation.latitude))
                .zoom(15.0)
                .build(),
            MapAnimationOptions.mapAnimationOptions {
                duration(500L)
            }
        )

    pointAnnotationManager?.deleteAll()
    addMarkerToMap(
        location = currentLocation,
        context = mapView.context,
        pointAnnotationManager = pointAnnotationManager,
    )
}
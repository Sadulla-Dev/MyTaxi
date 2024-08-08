package com.example.mytaxi.presentation.utils

import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mapbox.maps.plugin.animation.flyTo

fun MapView?.zoomIn() {
    this?.getMapboxMap()?.flyTo(
        CameraOptions.Builder()
            .zoom(this.getMapboxMap().cameraState.zoom + 0.5)
            .build(),
        MapAnimationOptions.mapAnimationOptions {
            duration(200L)
        }
    )
}

fun MapView?.zoomOut() {
    this?.getMapboxMap()?.flyTo(
        CameraOptions.Builder()
            .zoom(this.getMapboxMap().cameraState.zoom - 0.5)
            .build(),
        MapAnimationOptions.mapAnimationOptions {
            duration(200L)
        }
    )
}
package com.example.mytaxi.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.mytaxi.presentation.utils.addMarkerToMap
import com.example.mytaxi.presentation.utils.showUserLocation
import com.example.mytaxi.presentation.utils.zoomIn
import com.example.mytaxi.presentation.utils.zoomOut
import com.google.android.gms.maps.model.LatLng
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.attribution.attribution
import com.mapbox.maps.plugin.compass.compass
import com.mapbox.maps.plugin.logo.logo
import com.mapbox.maps.plugin.scalebar.scalebar

@Composable
fun MapScreen(currentLocation: LatLng) {
    val context = LocalContext.current

    var mapView by remember { mutableStateOf(MapView(context)) }
    var pointAnnotationManager by remember { mutableStateOf<PointAnnotationManager?>(null) }
    val mapStyle = if (isSystemInDarkTheme()) {
        Style.DARK
    } else {
        Style.LIGHT
    }

    LaunchedEffect(currentLocation) {
        pointAnnotationManager?.deleteAll()
        addMarkerToMap(
            location = currentLocation,
            context = context,
            pointAnnotationManager = pointAnnotationManager
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { ctx ->
                MapView(ctx, mapInitOptions = MapInitOptions(ctx)).apply {
                    mapView = this
                    getMapboxMap().apply {
                        loadStyle(mapStyle)
                        setCamera(
                            CameraOptions.Builder()
                                .center(
                                    Point.fromLngLat(
                                        currentLocation.longitude,
                                        currentLocation.latitude
                                    )
                                )
                                .zoom(15.0)
                                .build()
                        )
                    }
                    pointAnnotationManager = annotations.createPointAnnotationManager()
                    scalebar.enabled = false
                    compass.enabled = false
                    logo.enabled = false
                    attribution.enabled = false
                }
            },
            modifier = Modifier.fillMaxSize()
        )
        MapActions(
            modifier = Modifier.align(Alignment.CenterEnd),
            onPlusClick = { mapView.zoomIn() },
            onMinusClick = { mapView.zoomOut() },
            onLocationClick = {
                showUserLocation(
                    mapView = mapView,
                    currentLocation = currentLocation,
                    pointAnnotationManager = pointAnnotationManager,
                )
            }
        )
    }
}

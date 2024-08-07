@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.mytaxi.components

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import com.example.mytaxi.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.attribution.attribution
import com.mapbox.maps.plugin.compass.compass
import com.mapbox.maps.plugin.logo.logo
import com.mapbox.maps.plugin.scalebar.scalebar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

@Composable
fun MapScreen(content: @Composable (MapView, PointAnnotationManager?) -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var mapView by remember { mutableStateOf<MapView>(MapView(context)) }
    var pointAnnotationManager by remember { mutableStateOf<PointAnnotationManager?>(null) }
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    val isDarkTheme = isSystemInDarkTheme()
    val mapStyle = if (isDarkTheme) {
        Style.DARK
    } else {
        Style.LIGHT
    }

    LaunchedEffect(permissionState.status) {
        if (permissionState.status.isGranted) {
            scope.launch {
                val currentLocation = getCurrentLocation(context)
                currentLocation?.let {
                    mapView?.getMapboxMap()?.setCamera(
                        CameraOptions.Builder()
                            .center(Point.fromLngLat(it.longitude, it.latitude))
                            .zoom(15.0)
                            .build()
                    )
                    addMarkerToMap(context, pointAnnotationManager, it)
                }
            }
        } else {
            permissionState.launchPermissionRequest()
        }
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
                                .center(Point.fromLngLat(69.334525, 41.338543))
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
        content(mapView, pointAnnotationManager)
    }
}

fun zoomIn(mapView: MapView?) {
    mapView?.getMapboxMap()?.flyTo(
        CameraOptions.Builder()
            .zoom(mapView.getMapboxMap().cameraState.zoom + 1.0)
            .build(),
        MapAnimationOptions.mapAnimationOptions {
            duration(500L)
        }
    )
}

fun zoomOut(mapView: MapView?) {
    mapView?.getMapboxMap()?.flyTo(
        CameraOptions.Builder()
            .zoom(mapView.getMapboxMap().cameraState.zoom - 1.0)
            .build(),
        MapAnimationOptions.mapAnimationOptions {
            duration(500L)
        }
    )
}

fun showUserLocation(
//    context: Context,
    mapView: MapView,
    pointAnnotationManager: PointAnnotationManager?
) {
    val scope = CoroutineScope(Dispatchers.Main)
    scope.launch {
        val currentLocation = getCurrentLocation(mapView.context)
        currentLocation?.let {
            mapView.getMapboxMap().flyTo(
                CameraOptions.Builder()
                    .center(Point.fromLngLat(it.longitude, it.latitude))
                    .zoom(15.0)
                    .build(),
                MapAnimationOptions.mapAnimationOptions {
                    duration(500L)
                }
            )
            addMarkerToMap(mapView.context, pointAnnotationManager, it)
        }
    }
}

fun addMarkerToMap(
    context: Context,
    pointAnnotationManager: PointAnnotationManager?,
    location: LatLng
) {
    pointAnnotationManager?.let { manager ->
        val bitmap = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_marker, null)
            ?.let { drawable ->
                if (drawable is BitmapDrawable) {
                    drawable.bitmap
                } else {
                    val bitmap = Bitmap.createBitmap(
                        drawable.intrinsicWidth,
                        drawable.intrinsicHeight,
                        Bitmap.Config.ARGB_8888
                    )
                    val canvas = Canvas(bitmap)
                    drawable.setBounds(0, 0, canvas.width, canvas.height)
                    drawable.draw(canvas)
                    bitmap
                }
            }

        val resizedBitmap = bitmap?.let { Bitmap.createScaledBitmap(it, 150, 150, false) }

        val pointAnnotationOptions: PointAnnotationOptions? = resizedBitmap?.let {
            PointAnnotationOptions()
                .withPoint(Point.fromLngLat(location.longitude, location.latitude))
                .withIconImage(it)
        }

        pointAnnotationOptions?.let {
            manager.create(it)
        }
    }
}

suspend fun getCurrentLocation(context: Context): LatLng? = suspendCancellableCoroutine { cont ->
    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    val locationTask: Task<Location> = fusedLocationClient.lastLocation

    locationTask.addOnSuccessListener { location ->
        location?.let {
            cont.resume(LatLng(it.latitude, it.longitude))
        } ?: cont.resume(null)
    }

    locationTask.addOnFailureListener {
        cont.resume(null)
    }
}

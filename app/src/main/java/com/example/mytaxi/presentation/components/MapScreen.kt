package com.example.mytaxi.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.mytaxi.presentation.theme.MyTaxiTheme
import com.example.mytaxi.presentation.theme.ThemedPreview
import com.example.mytaxi.presentation.utils.showUserLocation
import com.example.mytaxi.presentation.utils.zoomIn
import com.example.mytaxi.presentation.utils.zoomOut
import com.google.android.gms.maps.model.LatLng
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
fun MapScreen(
    currentLocation: LatLng,
    bottomSheetProgress: Float,
    isLoading: Boolean = false,
) {
    val context = LocalContext.current

    var mapView by remember { mutableStateOf(MapView(context)) }
    var pointAnnotationManager by remember { mutableStateOf<PointAnnotationManager?>(null) }
    val mapStyle = if (isSystemInDarkTheme()) {
        Style.DARK
    } else {
        Style.LIGHT
    }

    val slideOffset by animateDpAsState(
        targetValue = 70.dp * bottomSheetProgress,
        label = ""
    )

    Box(modifier = Modifier.fillMaxSize()) {
        if (!isLoading) {
            AndroidView(
                factory = { context ->
                    MapView(context, mapInitOptions = MapInitOptions(context)).apply {
                        mapView = this
                        pointAnnotationManager = annotations.createPointAnnotationManager()
                        getMapboxMap().apply {
                            loadStyle(mapStyle) {
                                showUserLocation(
                                    mapView = mapView,
                                    currentLocation = currentLocation,
                                    pointAnnotationManager = pointAnnotationManager
                                )
                            }
                        }
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
                slideOffset = slideOffset,
                onPlusClick = { mapView.zoomIn() },
                onMinusClick = { mapView.zoomOut() },
                onLocationClick = {
                    showUserLocation(
                        mapView = mapView,
                        currentLocation = currentLocation,
                        pointAnnotationManager = pointAnnotationManager,
                    )
                },
            )
        }
    }
}


@ThemedPreview
@Composable
private fun MapScreenPreview() = MyTaxiTheme {
    MapScreen(
        currentLocation = LatLng(41.311081, 69.240562),
        bottomSheetProgress = 0f
    )
}
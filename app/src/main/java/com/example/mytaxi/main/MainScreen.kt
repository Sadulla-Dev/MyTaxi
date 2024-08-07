package com.example.mytaxi.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.mytaxi.components.MapActions
import com.example.mytaxi.components.MapScreen
import com.example.mytaxi.components.TopBarActions
import com.example.mytaxi.components.showUserLocation
import com.example.mytaxi.components.zoomIn
import com.example.mytaxi.components.zoomOut


@Composable
fun MainScreen() {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        MapScreen { mapView, pointAnnotationManager ->
            TopBarActions(
                modifier = Modifier
                    .align(Alignment.TopStart)
            )
            MapActions(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                onPlusClick = { zoomIn(mapView) },
                onMinusClick = { zoomOut(mapView) },
                onLocationClick = {
                    showUserLocation(
                        context = context,
                        mapView,
                        pointAnnotationManager
                    )
                }
            )
        }
    }
}

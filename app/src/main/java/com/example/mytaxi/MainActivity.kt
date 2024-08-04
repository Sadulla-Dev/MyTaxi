@file:OptIn(MapboxExperimental::class)

package com.example.mytaxi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mytaxi.ui.theme.MyTaxiTheme
import com.example.mytaxi.ui.theme.ThemedPreview
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTaxiTheme {
                MapboxMapScreen()
            }
        }
    }
}

@Composable
fun MapboxMapScreen() {
    MapboxMap(
        Modifier.fillMaxSize(),
        mapViewportState = MapViewportState().apply {
            setCameraOptions {
                zoom(2.0)
                center(Point.fromLngLat(-98.0, 39.5))
                pitch(0.0)
                bearing(0.0)
            }
        },
    )
}

@ThemedPreview
@Composable
fun GreetingPreview() {
    MyTaxiTheme {
        MapboxMapScreen()
    }
}
package com.example.mytaxi.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mytaxi.components.BottomSheetContent
import com.example.mytaxi.components.MapActions
import com.example.mytaxi.components.MapScreen
import com.example.mytaxi.components.TopBarActions
import com.example.mytaxi.components.showUserLocation
import com.example.mytaxi.components.zoomIn
import com.example.mytaxi.components.zoomOut
import com.example.mytaxi.ui.theme.MyTaxiColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val bottomSheetState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        sheetContent = {
            BottomSheetContent()
        },
        sheetContainerColor = MyTaxiColors.onBackground,
        sheetPeekHeight = 100.dp,
        sheetDragHandle = {},
        sheetShape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            MapScreen { mapView, pointAnnotationManager ->
                TopBarActions(
                    modifier = Modifier.align(Alignment.TopStart)
                )
                MapActions(modifier = Modifier.align(Alignment.CenterEnd),
                    onPlusClick = { zoomIn(mapView) },
                    onMinusClick = { zoomOut(mapView) },
                    onLocationClick = {
                        showUserLocation(
                            mapView, pointAnnotationManager
                        )
                    })
            }
        }
    }
}



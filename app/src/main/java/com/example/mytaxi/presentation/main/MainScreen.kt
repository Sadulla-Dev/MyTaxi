@file:OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)

package com.example.mytaxi.presentation.main

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mytaxi.presentation.components.BottomSheetContent
import com.example.mytaxi.presentation.components.MapScreen
import com.example.mytaxi.presentation.components.TopBarActions
import com.example.mytaxi.presentation.theme.MyTaxiColors
import com.example.mytaxi.presentation.theme.MyTaxiCornerRadius
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.LatLng

@Composable
fun MainScreen(
    currentLocation: LatLng,
    getLocation: () -> Unit,
) {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(permissionState.status) {
        if (permissionState.status.isGranted) {
            getLocation()
        } else {
            permissionState.launchPermissionRequest()
        }
    }

    val bottomSheetProgress = remember {
        derivedStateOf {
            val fraction = when (bottomSheetState.bottomSheetState.targetValue) {
                SheetValue.PartiallyExpanded -> 0f
                SheetValue.Expanded -> 1f
                else -> 0f
            }
            fraction
        }
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        sheetContent = { BottomSheetContent() },
        sheetContainerColor = MyTaxiColors.onBackground,
        sheetPeekHeight = 105.dp,
        sheetDragHandle = {},
        sheetShape = RoundedCornerShape(
            topStart = MyTaxiCornerRadius.huge,
            topEnd = MyTaxiCornerRadius.huge
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            MapScreen(
                currentLocation = currentLocation,
                bottomSheetProgress = bottomSheetProgress.value
            )
            TopBarActions(modifier = Modifier.align(Alignment.TopStart))
        }
    }
}



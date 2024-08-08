@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.mytaxi.presentation.main

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mytaxi.presentation.components.BottomSheetContent
import com.example.mytaxi.presentation.components.MapScreen
import com.example.mytaxi.presentation.components.TopBarActions
import com.example.mytaxi.presentation.theme.MyTaxiColors
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.LatLng

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    currentLocation: LatLng,
    getLocation: () -> Unit,
) {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    var hasRequestedPermission by remember { mutableStateOf(false) }
    var locationState by remember { mutableStateOf(false) }

    LaunchedEffect(permissionState.status) {
        if (permissionState.status.isGranted) {
            getLocation()
        } else {
            permissionState.launchPermissionRequest()
        }
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        sheetContent = { BottomSheetContent() },
        sheetContainerColor = MyTaxiColors.onBackground,
        sheetPeekHeight = 100.dp,
        sheetDragHandle = {},
        sheetShape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            MapScreen(currentLocation = currentLocation)
            TopBarActions(modifier = Modifier.align(Alignment.TopStart))
        }
    }
}



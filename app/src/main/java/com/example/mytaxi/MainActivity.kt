package com.example.mytaxi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.mytaxi.components.TopBarActions
import com.example.mytaxi.ui.theme.MyTaxiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTaxiTheme {
                val context = LocalContext.current
                Box() {
                    MapboxMapScreen()
                    TopBarActions(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                    )
                }
            }
        }
    }
}

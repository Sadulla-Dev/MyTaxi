package com.example.mytaxi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mytaxi.main.MainScreen
import com.example.mytaxi.ui.theme.MyTaxiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTaxiTheme {
                MainScreen()
            }
        }
    }
}
package com.kypeli.mushrooms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kypeli.mushrooms.ui.screens.MainScreen
import com.kypeli.mushrooms.ui.theme.MushroomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MushroomTheme {
                MainScreen()
            }
        }
    }
}

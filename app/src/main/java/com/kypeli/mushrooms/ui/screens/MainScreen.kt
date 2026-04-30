package com.kypeli.mushrooms.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.kypeli.mushrooms.navigation.NavKeys


@Composable
fun MainScreen() {
    val backStack = rememberNavBackStack(NavKeys.Home)
    NavDisplay(
        backStack = backStack,
        onBack = { if (backStack.size > 1) backStack.removeAt(backStack.lastIndex) },
        entryProvider = { key ->
            when (val myKey = key as? NavKeys) {
                NavKeys.Home -> NavEntry(key) {
                    IdentificationScreen(
                        onBackClick = {
                            backStack.removeAt(backStack.lastIndex)
                        }
                    )
                }


                null -> NavEntry(key) { Text("Invalid key") }
            }
        }
    )
}
package com.kypeli.mushrooms.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.kypeli.mushrooms.R
import com.kypeli.mushrooms.navigation.NavKeys

@Composable
fun MainScreen() {
    val backStack = rememberNavBackStack(NavKeys.Home)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (val myKey = key as? NavKeys) {
                NavKeys.Home -> {
                    NavEntry(key) {
                        IdentificationScreen(
                            onBackClick = {
                                backStack.removeLastOrNull()
                            },
                        )
                    }
                }

                null -> {
                    NavEntry(key) { Text(stringResource(R.string.invalid_key)) }
                }
            }
        },
    )
}

package com.kypeli.mushrooms.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Sealed interface representing navigation destinations in the app.
 * Each entry corresponds to a specific screen or navigation point.
 */
@Serializable
sealed interface NavKeys : NavKey {
    /**
     * The home screen destination.
     */
    @Serializable
    data object Home : NavKeys
}

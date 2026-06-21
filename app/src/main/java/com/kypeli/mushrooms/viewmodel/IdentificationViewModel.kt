package com.kypeli.mushrooms.viewmodel

import android.app.Application
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kypeli.mushrooms.R
import com.kypeli.mushrooms.data.GeminiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/** Represents the possible states of the mushroom identification UI. */
sealed class UiState {
    /** No identification has been requested yet. */
    data object Idle : UiState()

    /** An identification request is in progress. */
    data object Loading : UiState()

    /**
     * Identification completed successfully.
     * @property result The description returned by Gemini.
     */
    data class Success(
        val result: String,
    ) : UiState()

    /**
     * Identification failed.
     * @property message A human-readable description of the failure.
     */
    data class Error(
        val message: String,
    ) : UiState()
}

/**
 * ViewModel for the mushroom identification screen.
 *
 * This ViewModel handles the logic for identifying mushrooms from a provided image URI.
 * It manages the following responsibilities:
 * - Decodes the image from the given [Uri].
 * - Interacts with [GeminiService] to obtain an identification description.
 * - Manages and exposes the current [UiState] to the UI.
 *
 * @param application The [Application] instance, required by [AndroidViewModel].
 */
class IdentificationViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val geminiService = GeminiService()

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)

    /** Observable state driven by the most recent call to [identifyPhoto]. */
    val uiState: StateFlow<UiState> = _uiState

    /**
     * Decodes the image at [uri] and asks Gemini to identify the mushroom in it.
     *
     * Transitions [uiState] through [UiState.Loading] and then to either
     * [UiState.Success] or [UiState.Error]. Image decoding runs on [Dispatchers.IO];
     * software allocation is forced so the bitmap is always readable on the CPU.
     *
     * @param uri Content URI of the photo to analyse.
     */
    fun identifyPhoto(uri: Uri) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val bitmap =
                    withContext(Dispatchers.IO) {
                        val source =
                            ImageDecoder.createSource(
                                getApplication<Application>().contentResolver,
                                uri,
                            )
                        ImageDecoder.decodeBitmap(source) { decoder, _, _ ->
                            decoder.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
                        }
                    }
                if (bitmap == null) {
                    _uiState.value = UiState.Error(getApplication<Application>().getString(R.string.error_failed_to_load_image))
                    return@launch
                }
                val result = geminiService.describePicture(bitmap)
                _uiState.value =
                    if (result != null) {
                        UiState.Success(result)
                    } else {
                        UiState.Error(getApplication<Application>().getString(R.string.error_failed_to_identify))
                    }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}

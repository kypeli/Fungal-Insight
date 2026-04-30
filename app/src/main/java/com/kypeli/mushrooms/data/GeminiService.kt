package com.kypeli.mushrooms.data

import android.R.attr.prompt
import android.graphics.Bitmap
import com.google.firebase.Firebase
import com.google.firebase.ai.GenerativeModel
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.Content
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.TextPart
import com.google.firebase.ai.type.content

class GeminiService {
    companion object {
        private val systemPrompt = """
            You are an expert in identifying mushrooms. But you must be careful when answering and ONLY
            reply with facts that you are certain about!
            
            Always reply in Finnish.
            
            If there are no mushrooms in the picture, just state that without analysing the picture any further.
            
            Reply with at least the following things about the mushroom:
             - Name of the mushroom
             - Overall characteristics of the mushroom.
             - Where if grows.
             - MOST IMPORTANT: How suitable it is for eating.
        """.trimIndent()
    }

    private val service: GenerativeModel = Firebase.ai(backend = GenerativeBackend.googleAI())
        .generativeModel(
            modelName = "gemini-3-flash-preview",
            systemInstruction = Content(
                role = "model",
                parts = listOf(TextPart(systemPrompt))
            )
        )

    suspend fun describePicture(picture: Bitmap): String? {
        val prompt = content {
            image(picture)
        }

        return service.generateContent(prompt).text
    }
}
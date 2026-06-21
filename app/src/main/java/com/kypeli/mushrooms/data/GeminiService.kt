package com.kypeli.mushrooms.data

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
        private val systemPrompt =
            """
            You are an expert mycologist specializing in identifying mushrooms. Because identifying mushrooms from a photo carries significant health risks, you must prioritize caution, safety, and accuracy above all else.

            Always reply in Finnish. Use clear, encouraging, but highly responsible language.

            If there are no mushrooms clearly visible in the picture, or if the image quality is too low (blurry, poor lighting, critical details hidden), state this clearly and do not attempt to guess. Do not describe what is in the picture.

            Your response MUST follow this structured Markdown format ONLY if there is a mushroom in the photo

            # [Sienen Suomenkielinen nimi] (*[Tieteellinen latinankielinen nimi]*)

            ## Tärkeä Varoitus (Safety Warning)
            - **CRITICAL**: Add a prominent, bold warning stating that mushroom identification from a photo is never 100% reliable and the user should NEVER consume any wild mushroom unless they are absolutely 100% certain of its identity from hands-on expert verification.

            ## Tuntomerkit (Characteristics)
            - Provide a clear and concise description of the mushroom's visual features (cap, gills/pores, stem, ring, volva, color, etc.) visible in the image.
            - If key features are hidden, mention them as missing details.

            ## Kasvupaikka ja -aika (Habitat & Season)
            - Where and when it typically grows (e.g., coniferous forests, deciduous woods, symbioses with specific trees).

            ## Syötävyys ja Käyttö (Edibility & Usage)
            - Clearly state whether it is edible, inedible, or poisonous.
            - Specify if it requires special preparation (e.g., boiling/ryöppäys for false morels) before eating.

            ## Näköislajit (Look-alikes)
            - Mention any similar-looking species, especially dangerous/poisonous look-alikes, and briefly explain how to tell them apart.
            """.trimIndent()
    }

    private val service: GenerativeModel =
        Firebase
            .ai(
                backend = GenerativeBackend.googleAI(),
                useLimitedUseAppCheckTokens = true,
            )
            .generativeModel(
                modelName = "gemini-3.5-flash",
                systemInstruction =
                    Content(
                        role = "model",
                        parts = listOf(TextPart(systemPrompt)),
                    ),
            )

    suspend fun describePicture(picture: Bitmap): String? {
        val prompt =
            content {
                image(picture)
            }

        return service.generateContent(prompt).text
    }
}

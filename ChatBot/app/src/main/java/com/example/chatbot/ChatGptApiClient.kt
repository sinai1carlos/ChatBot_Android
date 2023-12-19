package com.example.chatbot
import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
/*
class ChatGptApiClient(private val apiKey: String) {

    private val client = OkHttpClient.Builder().build()

    fun sendMessage(message: String): String? {
        val url = "https://api.openai.com/v1/chat/completions"

        val json = """
        {
            "messages": [
                {"role": "system", "content": "You are a helpful assistant."},
                {"role": "user", "content": "$message"}
            ]
        }
    """.trimIndent()

        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        val body = json.toRequestBody(mediaType)

        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $apiKey")
            .post(body)
            .build()

        // Antes de la solicitud
        Log.d("ChatGptApiClient", "Antes de realizar la solicitud")

        val response = client.newCall(request).execute()

        Log.d("ChatGptApiClient", "Después de realizar la solicitud")




        return try {

            if (response.isSuccessful) {
                response.body?.string()
            } else {

                println("Error en la respuesta: ${response.code} - ${response.message}")
                null
            }
        } catch (e: Exception) {

            e.printStackTrace()
            null
        }
    }

}
*/
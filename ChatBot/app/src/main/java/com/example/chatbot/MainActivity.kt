package com.example.chatbot

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chatbot.ui.theme.ChatBotTheme
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(val role: String, val content: String)


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatBotTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChatScreen()
                }
            }
        }
    }
}


class ChatGptApiClient(private val apiKey: String) {

    private val client = OkHttpClient.Builder().build()

    suspend fun sendMessage(message: String): String? {
        val url = "https://api.openai.com/v1/chat/completions"


        val json = Gson().toJson(
            listOf(
                ChatMessage("system", "You are a helpful assistant."),
                ChatMessage("user", message)
            )
        )

        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        val body = json.toRequestBody(mediaType)

        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $apiKey")
            .post(body)
            .build()

        return try {
            val response = withContext(Dispatchers.IO) {
                client.newCall(request).execute()
            }

            if (response.isSuccessful) {
                response.body?.string()
            } else {
                Log.e("ChatGptApiClient", "Error en la respuesta: ${response.code} - ${response.message}")
                null
            }
        } catch (e: Exception) {
            Log.e("ChatGptApiClient", "Error al realizar la solicitud", e)
            null
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    var chatbotResponse by remember { mutableStateOf<String?>("") }
    var newMessage by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = newMessage,
            onValueChange = { newMessage = it },
            label = { Text("Escribe un mensaje") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    try {
                        chatbotResponse =
                            ChatGptApiClient("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").sendMessage(newMessage)

                        Log.d("ChatScreen", "Respuesta del servidor: $chatbotResponse")

                        newMessage = ""
                    } catch (e: Exception) {
                        Log.e("ChatScreen", "Error al enviar mensaje", e)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Enviar")
        }

        if (!chatbotResponse.isNullOrBlank()) {
            Text("Respuesta del Chatbot: $chatbotResponse")
        }
    }
}

package com.example.chatbot

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import com.example.chatbot.*
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {

    var chatbotResponse by remember { mutableStateOf<String?>("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {


        var newMessage by remember { mutableStateOf("") }
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


                    try {

                        chatbotResponse =
                            ChatGptApiClient("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").toString().sendMessage(newMessage)


                        Log.d("ChatScreen", "Respuesta del servidor: $chatbotResponse")


                        newMessage = ""
                    } catch (e: Exception) {

                        Log.e("ChatScreen", "Error al enviar mensaje", e)
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
*/
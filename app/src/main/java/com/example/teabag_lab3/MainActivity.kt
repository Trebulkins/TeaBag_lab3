package com.example.teabag_lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.teabag_lab3.ui.theme.TeaBag_lab3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TeaBag_lab3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    var sum by remember { mutableStateOf("") }
    var foods by remember { mutableStateOf("") }
    Column(
        Modifier.padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text="Калькулятор чаевых",
            modifier = Modifier.padding(vertical = 10.dp),
            fontSize = 6.em,
            fontWeight = FontWeight.Bold
        )

        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 10.dp)) {
            val trailingIconView = @Composable {
                Icon(
                    painter = painterResource(R.drawable.baseline_currency_ruble_24),
                    contentDescription = "Rubles",
                    tint = Color.Black
                )
            }

            Text(
                text = "Сумма заказа",
                Modifier.padding(horizontal = 20.dp)
            )
            TextField(
                modifier = modifier.padding(end = 20.dp),
                value = sum,
                onValueChange = { sum = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = trailingIconView,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFFF69B4),
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                )
            )
        }

        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 10.dp)) {

            Text(
                text = "Кол-во блюд",
                Modifier.padding(horizontal = 20.dp)
            )
            TextField(
                modifier = Modifier.padding(end=20.dp),
                value = foods,
                onValueChange = { foods = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFFF69B4),
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TeaBag_lab3Theme {
        AppScreen()
    }
}
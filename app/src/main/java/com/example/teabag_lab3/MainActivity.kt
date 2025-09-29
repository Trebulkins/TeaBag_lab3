package com.example.teabag_lab3

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.teabag_lab3.ui.theme.TeaBag_lab3Theme
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TeaBag_lab3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppScreen(modifier = Modifier.padding(innerPadding), LocalContext.current)
                }
            }
        }
    }
}

@Composable
fun AppScreen(modifier: Modifier = Modifier, context: Context) {
    var sum by remember { mutableStateOf("") }
    var foods by remember { mutableStateOf("") }
    var tea by remember { mutableFloatStateOf(0f) }

    val skids = listOf(3, 5, 7, 10)
    var (skid, _) = remember { mutableIntStateOf(0) }

    var overall by remember { mutableFloatStateOf(0f) }
    
    Column(
        Modifier.padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text="Калькулятор чаевых",
            modifier = Modifier.padding(vertical = 12.dp),
            fontSize = 6.em,
            fontWeight = FontWeight.Bold
        )

        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically) {
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
                onValueChange = { sum = it; if (sum != "") overall = sum.toFloat() * (1 + tea / 100) * (1 - skid / 100) },
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
            verticalAlignment = Alignment.CenterVertically) {

            Text(
                text = "Кол-во блюд",
                Modifier.padding(horizontal = 20.dp)
            )
            TextField(
                modifier = Modifier.padding(end=20.dp),
                value = foods,
                onValueChange = { foods = it;
                    if (foods != "") skid = when (foods.toInt()) {
                        1, 2 -> 3
                        3, 4, 5 -> 5
                        6, 7, 8, 9, 10 -> 7
                        else -> 10
                    };
                    Toast.makeText(context, "$foods, $skid", Toast.LENGTH_SHORT).show();
                    if (foods != "" && sum != "") overall = sum.toFloat() * (1 + tea / 100) * (1 - skid / 100)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFFF69B4),
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                )
            )
        }

        Text(text = "Чаевые", modifier = Modifier.padding(top = 30.dp))
        Text(text = "${round(tea)}%")
        Slider(
            value = tea,
            onValueChange = { tea = it; if (sum != "") overall = sum.toFloat() * (1 + tea / 100) * (1 - skid / 100) },
            steps = 24,
            valueRange = 0f .. 25f,
            modifier = Modifier.padding(horizontal = 22.dp)
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .width(360.dp)
                .padding(vertical = 10.dp)){
            Text(text = "0")
            Text(text = "25")
        }

        Row(modifier = Modifier.padding(top = 30.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(text = "Скидка")
            skids.forEach { text ->
                RadioButton(
                    selected = (text == skid),
                    onClick = null
                )
                Text(text = "$text%")
            }
        }

        Text(
            modifier = Modifier.padding(top = 30.dp),
            text = "Итого: ${overall} руб.")
    }
}
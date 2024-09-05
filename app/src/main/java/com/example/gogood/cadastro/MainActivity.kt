package com.example.gogood.cadastro

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogood.R
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerInput(
    label: String,
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)


    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            val formattedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
            onDateSelected(formattedDate)
        }, year, month, day
    )

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = selectedDate,
            onValueChange = {},
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.White,
                containerColor = Color.White
            ),
            label = { Text(label) },
            modifier = Modifier
                .clickable { datePickerDialog.show() }
                .padding(8.dp)
                .border(1.dp, Color.Black, MaterialTheme.shapes.small),
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = "Ícone de calendário",
                    modifier = Modifier
                        .clickable { datePickerDialog.show() }
                        .padding(1.dp)
                        .size(20.dp),
                )
            },
            enabled = false
        )

    }

}

@Composable
fun MyApp() {
    var selectedDate by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        DatePickerInput(
            label = "dd/MM/yyyy",
            selectedDate = selectedDate,
            onDateSelected = { date -> selectedDate = date }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}

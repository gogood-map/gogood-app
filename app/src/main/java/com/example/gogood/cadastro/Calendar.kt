package com.example.gogood.cadastro

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogood.R
import com.example.gogood.menu.Menu
import com.example.gogood.ui.theme.GoGoodTheme
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoGoodTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Menu(modifier = Modifier.padding(innerPadding))
                }
            }
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

    Column(
        verticalArrangement = Arrangement.Center,
    ) {

        TextField(
            value = selectedDate,
            onValueChange = {},
            textStyle = MaterialTheme.typography.bodySmall,
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.White,
                containerColor = Color.White
            ),
            label = { Text(text = label, fontSize = MaterialTheme.typography.bodySmall.fontSize) },
            modifier = modifier
                .clickable { datePickerDialog.show() }
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

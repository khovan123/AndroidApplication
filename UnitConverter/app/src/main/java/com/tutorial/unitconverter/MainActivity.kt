package com.tutorial.unitconverter

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutorial.unitconverter.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}
@Composable
fun UnitConverter() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iConversionFactor by remember { mutableDoubleStateOf(1.0) }
    var oConversionFactor by remember { mutableDoubleStateOf(1.0) }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    fun toggleIExpanded() {
        iExpanded = !iExpanded
    }

    fun toggleOExpanded() {
        oExpanded = !oExpanded
    }

    fun convertUnits(){
        val inputValueConvert = inputValue.toDoubleOrNull() ?:0.0
        val outputValueConvert = inputValueConvert * iConversionFactor / oConversionFactor
        outputValue = outputValueConvert.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Unit Converter",
            color = Color(0xBF000000),
            fontWeight = FontWeight.W500,
            style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()}
                ,
            label = { Text(text = "Enter Value") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Row() {
            Box( modifier = Modifier.padding(16.dp)) {
                Button(onClick = {
                    toggleIExpanded()
                }) {
                    Text(text = inputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "A Arrow Drop Down"
                    )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { toggleIExpanded() }) {
                    DropdownMenuItem(text = {
                        Text(text = "Millimeters")
                    },
                        onClick = {
                            toggleIExpanded()
                            inputUnit = "Millimeters"
                            iConversionFactor = 1.0;
                        })
                    DropdownMenuItem(text = {
                        Text(text = "Centimeters")
                    },
                        onClick = {
                            toggleIExpanded()
                            inputUnit = "Centimeters"
                            iConversionFactor = 10.0
                        })

                    DropdownMenuItem(text = {
                        Text(text = "Decimeters")
                    },
                        onClick = {
                            toggleIExpanded()
                            inputUnit = "Decimeters"
                            iConversionFactor = 100.0
                        })
                    DropdownMenuItem(text = {
                        Text(text = "Meters")
                    },
                        onClick = {
                            toggleIExpanded()
                            inputUnit = "Meters"
                            iConversionFactor = 1000.0
                        })
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
            Box(modifier = Modifier.padding(16.dp)) {
                Button(onClick = {
                    toggleOExpanded()
                }) {
                    Text(text = outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "A Arrow Drop Down"
                    )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { toggleOExpanded() }) {
                    DropdownMenuItem(text = {
                        Text(text = "Millimeters")
                    },
                        onClick = {
                            toggleOExpanded()
                            outputUnit = "Millimeters"
                            oConversionFactor = 1.0
                        })

                    DropdownMenuItem(text = {
                        Text(text = "Centimeters")
                    },
                        onClick = {
                            toggleOExpanded()
                            outputUnit = "Centimeters"
                            oConversionFactor = 10.0
                        })

                    DropdownMenuItem(text = {
                        Text(text = "Decimeters")
                    },
                        onClick = {
                            toggleOExpanded()
                            outputUnit = "Decimeters"
                            oConversionFactor = 100.0
                        })

                    DropdownMenuItem(text = {
                        Text(text = "Meters")
                    },
                        onClick = {
                            toggleOExpanded()
                            outputUnit = "Meters"
                            oConversionFactor = 1000.0
                        })
                }
            }
        }
        if(inputValue.toDoubleOrNull()!== null){
            convertUnits()
        }
        Text(
            text = "Result: $outputValue $outputUnit",
            color = Color(0xBF000000),
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            fontStyle = FontStyle.Normal,
            style = MaterialTheme.typography.headlineMedium )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}
package br.com.vpn.valet.ui.vehicles

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.vpn.valet.R
import br.com.vpn.valet.ui.components.Button
import br.com.vpn.valet.ui.home.Title
import br.com.vpn.valet.ui.theme.Yellow
import kotlin.math.exp

@Composable
fun NewVehicle(
    onBackPressed: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(
            modifier = modifier
                .background(MaterialTheme.colors.background)
                .fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
        )
        TopAppBar(onBackPressed = onBackPressed, modifier = modifier)
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .padding(24.dp)
                .fillMaxSize()
        ) {
            Column(
            ) {
                Title(title = "New vehicle", modifier = modifier)
                OutlinedDropdown(label = "Select a brand", modifier = modifier.padding(top = 8.dp))
                OutlinedTextField(label = "Model", modifier = modifier)
                OutlinedTextField(label = "License", modifier = modifier)
            }
            Button(text = "Add", modifier = modifier) {

            }
        }
    }
}

@Composable
fun TopAppBar(onBackPressed: () -> Unit, modifier: Modifier) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)) {
        IconButton(onClick = onBackPressed) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = "Back"
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OutlinedDropdown(
    label: String,
    modifier: Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedBrand by remember { mutableStateOf("") }
    val brands = listOf("Toyota", "Hyundai", "Honda", "Fiat")
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded } ) {
        OutlinedTextField(
            value = selectedBrand,
            label = { Text(text = label) },
            onValueChange = {
                selectedBrand = it
                expanded = true },
            shape = RoundedCornerShape(8.dp),
            trailingIcon = {
                Icon(
                    icon,
                    if (expanded) "Expand" else "Contract",
                )
            },
            modifier = modifier.fillMaxWidth()
        )

        val filteredOptions = brands.filter { it.contains(selectedBrand, ignoreCase = true)}

        if (filteredOptions.isNotEmpty()) {
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = modifier.fillMaxWidth()
            ) {
                filteredOptions.forEach { brand ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        selectedBrand = brand
                    }) {
                        Text(text = brand)
                    }
                }
            }

        }
    }
}


//@Composable
//fun OutlinedDropdown(
//    label: String,
//    modifier: Modifier
//) {
//    var expanded by remember { mutableStateOf(false) }
//    var selectedBrand by remember { mutableStateOf("") }
//    val brands = listOf("Toyota", "Hyundai", "Honda", "Fiat")
//    val icon = if (expanded)
//        Icons.Filled.KeyboardArrowUp
//    else
//        Icons.Filled.KeyboardArrowDown
//
//    Column() {
//        OutlinedTextField(
//            value = selectedBrand,
//            label = { Text(text = label) },
//            onValueChange = { selectedBrand = it },
//            shape = RoundedCornerShape(8.dp),
//            trailingIcon = {
//                Icon(
//                    icon,
//                    "Expand",
//                    modifier = modifier.clickable { expanded = !expanded }
//                )
//            },
//            modifier = modifier.fillMaxWidth()
//        )
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
//            modifier = modifier.fillMaxWidth()
//        ) {
//            brands.forEach { brand ->
//                DropdownMenuItem(onClick = {
//                    expanded = false
//                    selectedBrand = brand
//                }) {
//                    Text(text = brand)
//                }
//            }
//        }
//    }
//}

@Composable
fun OutlinedTextField(
    label: String,
    modifier: Modifier
) {
    var userInput by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = userInput,
        onValueChange = { userInput = it },
        label = { Text(text = label) },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
        ),
        modifier = modifier.fillMaxWidth(),
    )
}
package br.com.vpn.valet.ui.vehicles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.vpn.valet.R
import br.com.vpn.valet.data.Vehicle
import br.com.vpn.valet.ui.components.Button
import br.com.vpn.valet.ui.home.Title

@Composable
fun NewVehicle(
    viewModel: NewVehicleViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onBackPressed: () -> Unit,
    modifier: Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

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

        var brand by remember {
            mutableStateOf("")
        }
        var model by remember {
            mutableStateOf("")
        }
        var license by remember {
            mutableStateOf("")
        }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .padding(24.dp)
                .fillMaxSize()
        ) {

            Column(
            ) {
                Title(title = "New vehicle", modifier = modifier)
                OutlinedDropdown(
                    value = brand,
                    onChange = { brand = it},
                    label = "Select a brand",
                    modifier = modifier.padding(top = 8.dp)
                )
                OutlinedTextField(
                    value = model,
                    onInputTextChange = { model = it },
                    label = "Model",
                    modifier = modifier
                )
                OutlinedTextField(
                    value = license,
                    onInputTextChange = { license = it },
                    label = "License",
                    modifier = modifier
                )
            }
            Button(text = "Add", modifier = modifier) {
                val vehicle = Vehicle(brand.uppercase(), model, license, "", false)
                viewModel.addVehicle(vehicle)
                onBackPressed()
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
    value: String,
    onChange: (String) -> Unit,
    label: String,
    modifier: Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val brands = listOf("Toyota", "Hyundai", "Honda", "Fiat")
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded } ) {
        OutlinedTextField(
            value = value,
            label = { Text(text = label) },
            onValueChange = {
                onChange(it)
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

        val filteredOptions = brands.filter { it.contains(value, ignoreCase = true)}

        if (filteredOptions.isNotEmpty()) {
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = modifier.fillMaxWidth()
            ) {
                filteredOptions.forEach { brand ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        onChange.invoke(brand)
                    }) {
                        Text(text = brand)
                    }
                }
            }

        }
    }
}

@Composable
fun OutlinedTextField(
    value: String,
    onInputTextChange: (String) -> Unit,
    label: String,
    modifier: Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onInputTextChange,
        label = { Text(text = label) },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
        ),
        modifier = modifier.fillMaxWidth(),
    )
}
package br.com.vpn.valet.ui.vehicles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.vpn.valet.data.Vehicle
import br.com.vpn.valet.ui.components.AppBar
import br.com.vpn.valet.ui.home.Title

@Composable
fun Vehicles(
    viewModel: VehicleViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onAddNewButtonClicked: () -> Unit,
    modifier: Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface() {
        Column(
            modifier
                .background(MaterialTheme.colors.background)
        ) {
            Spacer(
                modifier = modifier
                    .background(MaterialTheme.colors.background)
                    .fillMaxWidth()
                    .windowInsetsTopHeight(WindowInsets.statusBars)
            )
            AppBar(modifier = modifier)

            Column(
                modifier = modifier
                    .padding(
                        top = 24.dp,
                        start = 24.dp,
                        end = 24.dp,
                        bottom = 0.dp
                    ),
            ) {
                Title(title = "My vehicles", modifier = modifier)
                VehicleList(uiState, viewModel, modifier, onAddNewButtonClicked)
            }
        }
    }
}

@Composable
private fun VehicleList(
    uiState: VehiclesUiState,
    viewModel: VehicleViewModel,
    modifier: Modifier,
    onAddNewButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if (uiState.isLoading) {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
        LazyColumn(
            modifier = modifier.fillMaxWidth()
        ) {
            items(uiState.vehicles) { vehicle ->
                VehicleItem(vehicle, modifier) {
                    vehicle.id?.let(viewModel::deleteVehicle)
                }

                if (vehicle == uiState.vehicles.last())
                    br.com.vpn.valet.ui.components.Button(
                        text = "Add new vehicle",
                        modifier = modifier,
                        icon = { Icon(Icons.Default.Add, "Add") }
                    ) {
                        onAddNewButtonClicked()
                    }
                else
                    Box(
                        modifier
                            .fillMaxWidth()
                            .height(0.4.dp)
                            .background(MaterialTheme.colors.secondaryVariant)
                    )
            }
        }
    }

}


@Composable
fun VehicleItem(
    vehicle: Vehicle,
    modifier: Modifier,
    onDelete: () -> Unit,
) {
    Column() {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = modifier.padding(vertical = 16.dp)
            ) {
                Text(text = vehicle.model, style = MaterialTheme.typography.h3)
                Text(text = vehicle.license, color = MaterialTheme.colors.secondaryVariant)
                Text(text = vehicle.color ?: "Color not specified", color = MaterialTheme.colors.secondaryVariant)
            }

            IconButton(
                onClick = onDelete
            ) {
                Icon(
                    Icons.Outlined.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colors.error,
                    modifier = modifier
                        .padding(4.dp)
                )
            }
        }
    }
}

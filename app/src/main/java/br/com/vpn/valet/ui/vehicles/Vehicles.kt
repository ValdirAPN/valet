package br.com.vpn.valet.ui.vehicles

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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

    Scaffold(
        floatingActionButton = {
            androidx.compose.material.Button(
                onClick = { /*TODO*/ },
                modifier = modifier.size(60.dp),
                shape = RoundedCornerShape(60.dp)
            ) {
                Icon(Icons.Default.Add, "Add")
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = modifier.padding(innerPadding)
        ) {
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
                    VehicleList(uiState, modifier)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun VehicleList(
    uiState: VehiclesUiState,
    modifier: Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(

            )
        }
        LazyColumn(
            modifier = modifier
        ) {
            items(uiState.vehicles) { vehicle ->
                VehicleItem(vehicle, modifier)
            }
        }
    }

}

@Composable
fun VehicleItem(vehicle: Vehicle, modifier: Modifier) {
    Column() {
        Column(
            modifier = modifier.padding(vertical = 16.dp)
        ) {
            Text(text = vehicle.model, style = MaterialTheme.typography.h3)
            Text(text = vehicle.license, color = MaterialTheme.colors.secondaryVariant)
            Text(text = vehicle.color ?: "", color = MaterialTheme.colors.secondaryVariant)
        }
        Box(
            modifier
                .fillMaxWidth()
                .height(0.4.dp)
                .background(MaterialTheme.colors.secondaryVariant)
        )
    }
}

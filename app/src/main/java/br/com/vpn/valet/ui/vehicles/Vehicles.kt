package br.com.vpn.valet.ui.vehicles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.vpn.valet.data.Vehicle
import br.com.vpn.valet.ui.AppBar
import br.com.vpn.valet.ui.components.Button
import br.com.vpn.valet.ui.home.Title

@Composable
fun Vehicles(
    modifier: Modifier
) {
    Surface {
        Column(
            modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            val vehicles = listOf<Vehicle>(
                Vehicle("Fox", "MYW-7270", "Vermelho", true)
            )
            Spacer(
                modifier = modifier
                    .background(MaterialTheme.colors.background)
                    .fillMaxWidth()
                    .windowInsetsTopHeight(WindowInsets.statusBars)
            )
            AppBar(modifier = modifier)
            Column(
                modifier = modifier.padding(24.dp)
            ) {
                Title(title = "My vehicles", modifier = modifier)
                Column(
                    modifier = modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    LazyColumn(
                        modifier.padding(top = 16.dp)
                    ) {
                        items(vehicles) { vehicle ->
                            VehicleItem(vehicle, modifier)
                        }
                    }
                    Button(text = "Add new", modifier = modifier, )
                }
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
            Text(text = vehicle.color, color = MaterialTheme.colors.secondaryVariant)
        }
        Box(
            modifier
                .fillMaxWidth()
                .height(0.4.dp)
                .background(MaterialTheme.colors.secondaryVariant)
        )
    }
}

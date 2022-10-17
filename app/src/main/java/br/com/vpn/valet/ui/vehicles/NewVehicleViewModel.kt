package br.com.vpn.valet.ui.vehicles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vpn.valet.data.Vehicle
import br.com.vpn.valet.network.RetrofitNetwork
import br.com.vpn.valet.network.ValetRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

data class NewVehicleUiState(
    var brand: String = "",
    var model: String = "",
    val license: String = ""
)

class NewVehicleViewModel : ViewModel() {
    val valetNetwork = RetrofitNetwork()
    val valetRepository = ValetRepositoryImpl(valetNetwork)

    val uiState = MutableStateFlow(NewVehicleUiState())

    fun addVehicle(vehicle: Vehicle) {
        viewModelScope.launch {
            valetRepository.addVehicle(vehicle)
        }
    }
}
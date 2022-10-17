package br.com.vpn.valet.ui.vehicles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vpn.valet.data.Vehicle
import br.com.vpn.valet.network.RetrofitNetwork
import br.com.vpn.valet.network.ValetRepositoryImpl
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class VehiclesUiState(
    var vehicles: List<Vehicle> = emptyList(),
    var isLoading: Boolean = false
)

private data class VehiclesViewModelState(
    var vehicles: List<Vehicle> = emptyList(),
    var isLoading: Boolean = false
) {
    fun toUiState(): VehiclesUiState = VehiclesUiState(
        vehicles = vehicles,
        isLoading = isLoading
    )
}

class VehicleViewModel() : ViewModel(){
    val valetNetwork = RetrofitNetwork()
    val valetRepository = ValetRepositoryImpl(valetNetwork)

    private val viewModelState = MutableStateFlow(VehiclesViewModelState(isLoading = true))

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )


    init {
        viewModelScope.launch {
            val result = valetRepository.getVehicles()
            viewModelState.update { it.copy(vehicles = result, isLoading = false) }
        }
    }
}
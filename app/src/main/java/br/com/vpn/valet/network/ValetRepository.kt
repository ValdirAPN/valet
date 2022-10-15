package br.com.vpn.valet.network

import br.com.vpn.valet.data.Vehicle
import kotlinx.coroutines.flow.Flow

interface ValetRepository {
    suspend fun getVehicles(): List<Vehicle>
}

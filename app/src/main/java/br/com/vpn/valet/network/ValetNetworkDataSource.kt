package br.com.vpn.valet.network

import br.com.vpn.valet.data.Vehicle

interface ValetNetworkDataSource {
    suspend fun getVehicles(): List<Vehicle>
    suspend fun addVehicle(vehicle: Vehicle): Vehicle
}
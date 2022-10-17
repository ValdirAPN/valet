package br.com.vpn.valet.network

import br.com.vpn.valet.data.Vehicle

class ValetRepositoryImpl(
    private val network: ValetNetworkDataSource
) : ValetRepository {
    override suspend fun getVehicles(): List<Vehicle> =
        network.getVehicles()

    override suspend fun addVehicle(vehicle: Vehicle): Vehicle =
        network.addVehicle(vehicle)
}
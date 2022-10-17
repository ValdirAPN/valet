package br.com.vpn.valet.network

import br.com.vpn.valet.data.Vehicle
import retrofit2.Response
import retrofit2.http.*

interface ValetNetworkApi {

    @GET(value = "cars")
    suspend fun getVehicles() : List<Vehicle>

    @POST(value = "cars")
    suspend fun addVehicle(@Body vehicle: Vehicle) : Vehicle

    @DELETE(value = "cars/{id}")
    suspend fun deleteVehicle(@Path("id") id: String) : Response<Unit>
}
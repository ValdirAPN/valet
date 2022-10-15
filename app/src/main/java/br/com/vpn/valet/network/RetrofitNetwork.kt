package br.com.vpn.valet.network

import br.com.vpn.valet.data.Vehicle
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private data class NetworkResponse<T>(
    val data: T
)

private interface ValetNetworkApi {

    @GET(value = "cars")
    suspend fun getVehicles() : NetworkResponse<List<Vehicle>>
}

class RetrofitNetwork : ValetNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl("https://parking-vpn.herokuapp.com/")
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .build()
        )
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .build()
        .create(ValetNetworkApi::class.java)

    override suspend fun getVehicles(): List<Vehicle> =
        networkApi.getVehicles().data
}
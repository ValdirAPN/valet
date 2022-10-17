package br.com.vpn.valet.network

import br.com.vpn.valet.data.Vehicle
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private interface ValetNetworkApi {

    @GET(value = "cars")
    suspend fun getVehicles() : List<Vehicle>

    @POST(value = "cars")
    suspend fun addVehicle(@Body vehicle: Vehicle) : Vehicle
}

class BasicAuthInterceptor(
    username: String,
    password: String,
) : Interceptor {

    private val credentials: String = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
            .header("Authorization", credentials)
            .build()

        return chain.proceed(authenticatedRequest)
    }

}

class RetrofitNetwork : ValetNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl("https://parking-vpn.herokuapp.com/")
        .client(
            OkHttpClient.Builder()
                .addInterceptor(BasicAuthInterceptor("admin", "adminPass"))
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
        networkApi.getVehicles()

    override suspend fun addVehicle(vehicle: Vehicle): Vehicle =
        networkApi.addVehicle(vehicle)
}
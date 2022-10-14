package br.com.vpn.valet.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import br.com.vpn.valet.R
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Profile: Screen("profile")
    object Vehicles: Screen("vehicles")
    object ParkingLots: Screen("parking_lots")
    object NewVehicle: Screen("new_vehicle")
}

sealed class BottomNavItem(var title: String, var icon: Int, var scren_route: String) {
    object Home : BottomNavItem("Home", R.drawable.ic_home, "home")
    object Profile: BottomNavItem("Profile", R.drawable.ic_profile, "profile")
    object Vehicles: BottomNavItem("Vehicles", R.drawable.ic_car, "vehicles")
    object ParkingLots: BottomNavItem("ParkingLots", R.drawable.ic_ticket, "parking_lots")
}

@Composable
fun rememberValetAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController,  context) {
    ValetAppState(navController, context)
}

class ValetAppState(
    val navController: NavHostController,
    private val context: Context
) {
    var isOnline by mutableStateOf(checkIfOnline())
        private set

    fun refreshOnline() {
        isOnline = checkIfOnline()
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    private fun checkIfOnline(): Boolean {
        val cm = getSystemService(context, ConnectivityManager::class.java)

        val capabilities = cm?.getNetworkCapabilities(cm.activeNetwork) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }
}
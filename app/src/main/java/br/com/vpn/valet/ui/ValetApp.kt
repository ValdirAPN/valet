package br.com.vpn.valet.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.vpn.valet.ui.home.Home
import br.com.vpn.valet.ui.parking.Parking
import br.com.vpn.valet.ui.profile.Profile
import br.com.vpn.valet.ui.vehicles.Vehicles

@Composable
fun ValetApp(
    appState: ValetAppState = rememberValetAppState()
) {
    if (appState.isOnline) {
        Scaffold(bottomBar = { BottomNav(navController = appState.navController) }) { innerPadding ->
            NavHost(
                navController = appState.navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.route) {
                    Home()
                }
                composable(Screen.ParkingLots.route) {
                    Parking()
                }
                composable(Screen.Vehicles.route) {
                    Vehicles()
                }
                composable(Screen.Profile.route) {
                    Profile()
                }
            }
        }
    } else {
        OfflineDialog {
            appState.refreshOnline()
        }
    }
}

@Composable
fun BottomNav(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.ParkingLots,
        BottomNavItem.Vehicles,
        BottomNavItem.Profile
    )

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        val navBackStackTrace by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackTrace?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.scren_route,
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                onClick = {
                          navController.navigate(item.scren_route) {
                              navController.graph.startDestinationRoute?.let { screen_route ->
                                  popUpTo(screen_route) {
                                      saveState = true
                                  }
                              }
                              launchSingleTop = true
                              restoreState = true
                          }
                          },
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = item.title) }
            )
        }
    }
}

@Composable
fun OfflineDialog(onRetry: () -> Unit) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = { Text(text = "Connection error")},
        text = { Text(text = "Unable to fetch your data.\nCheck your internet connection and try again.")},
        confirmButton = {
            TextButton(onClick = onRetry) {
                Text(text = "Retry")
            }
        }
    )
}
package br.com.vpn.valet.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
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
    modifier: Modifier,
    appState: ValetAppState = rememberValetAppState()
) {

    if (appState.isOnline) {
        Scaffold(
            bottomBar = { BottomNav(navController = appState.navController) },
        ) { innerPadding ->
            NavHost(
                navController = appState.navController,
                startDestination = Screen.Home.route,
                modifier = modifier
                    .padding(innerPadding)
            ) {
                composable(Screen.Home.route) {
                    Home(modifier = modifier)
                }
                composable(Screen.ParkingLots.route) {
                    Parking(modifier = modifier)
                }
                composable(Screen.Vehicles.route) {
                    Vehicles(modifier = modifier)
                }
                composable(Screen.Profile.route) {
                    Profile(modifier = modifier)
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
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.secondary,
        modifier = Modifier.padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())
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
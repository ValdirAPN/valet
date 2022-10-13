package br.com.vpn.valet.ui

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.vpn.valet.ui.home.Home

@Composable
fun ValetApp(
    appState: ValetAppState = rememberValetAppState()
) {
    if (appState.isOnline) {
        NavHost(
            navController = appState.navController,
            startDestination = Screen.Home.route) {
            composable(Screen.Home.route) { backStackEntry ->
                Home()
            }
        }
    } else {
        OfflineDialog {
            appState.refreshOnline()
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
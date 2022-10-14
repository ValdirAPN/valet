package br.com.vpn.valet.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.vpn.valet.ui.AppBar
import br.com.vpn.valet.ui.home.Title

@Composable
fun Profile(
    modifier: Modifier
) {
    val menuItems = listOf("Personal data", "Payment", "Parking history", "Help", "Sign out")
    Column(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Spacer(
            modifier
                .background(MaterialTheme.colors.background)
                .fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
        )
        AppBar(modifier = modifier)
        Column(
            modifier.padding(24.dp)
        ) {
            Title(title = "My account", modifier = modifier)
            LazyColumn(
                modifier.padding(top = 16.dp)
            ) {
                items(menuItems) { item ->
                    MenuItem(item, modifier, item == menuItems.last())
                }
            }
        }
    }
}

@Composable
fun MenuItem(title: String, modifier: Modifier, isLast: Boolean = false) {
    Column {
        Text(
            text = title,
            modifier = modifier.padding(vertical = 16.dp),
            color = if (isLast) MaterialTheme.colors.error else MaterialTheme.colors.onBackground
        )
        if (!isLast) Box(modifier.fillMaxWidth().height(0.4.dp).background(MaterialTheme.colors.secondaryVariant))
    }
}
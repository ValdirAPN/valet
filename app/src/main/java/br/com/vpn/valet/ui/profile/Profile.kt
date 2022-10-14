package br.com.vpn.valet.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.vpn.valet.ui.AppBar

@Composable
fun Profile(
    modifier: Modifier
) {
    Surface {
        Column(
            modifier.fillMaxSize().background(MaterialTheme.colors.background)
        ) {
            Spacer(
                modifier
                    .background(MaterialTheme.colors.background)
                    .fillMaxWidth()
                    .windowInsetsTopHeight(WindowInsets.statusBars)
            )
            AppBar(modifier = modifier)
            Text(text = "Profile")
        }
    }
}
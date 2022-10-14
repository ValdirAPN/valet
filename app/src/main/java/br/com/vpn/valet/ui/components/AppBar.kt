package br.com.vpn.valet.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.vpn.valet.R

@Composable
fun AppBar (
    modifier: Modifier,
    isOnPrimary: Boolean = false
) {
    TopAppBar(
        title = {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.valet_logo),
                    colorFilter = ColorFilter.tint(
                        if (isOnPrimary) MaterialTheme.colors.background
                        else MaterialTheme.colors.primary
                    ),
                    contentDescription = null
                )
            }
        },
        modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}
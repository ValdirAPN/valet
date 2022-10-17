package br.com.vpn.valet.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Button(
    text: String,
    modifier: Modifier,
    icon: (@Composable()(() -> Unit))? = null,
    onClick: () -> Unit
) {
    androidx.compose.material.Button(
        onClick = { onClick() },
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        icon?.invoke()
        Text(text = text)
    }
}
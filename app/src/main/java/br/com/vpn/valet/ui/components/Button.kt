package br.com.vpn.valet.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Button(text: String, modifier: Modifier) {
    androidx.compose.material.Button(
        onClick = { /*TODO*/ },
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text)
    }
}
package br.com.vpn.valet.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.vpn.valet.R
import br.com.vpn.valet.ui.components.AppBar
import br.com.vpn.valet.ui.theme.Yellow

@Composable
fun Home(
    modifier: Modifier
) {
    Surface(
        modifier.fillMaxSize()
    ) {
        HomeContent(modifier = modifier)
    }
}

@Composable
fun HomeContent(
    modifier: Modifier
) {
    Column(
        modifier = modifier.background(MaterialTheme.colors.primary)
    ) {
        Spacer(
            modifier
                .background(MaterialTheme.colors.primary)
                .fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
        )
        AppBar(modifier = modifier, isOnPrimary = true)
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier.padding(24.dp)
            ) {
                Title(title = "Let's find a parking space", modifier = modifier, isOnPrimary = true)
                FindLocationTextField(
                    modifier
                        .padding(top = 24.dp)
                )
            }
            FindParkingMap()
        }
    }
}

@Composable
fun Title(
    title: String,
    modifier: Modifier,
    isOnPrimary: Boolean = false
) {
    Text(
        text = title,
        style = MaterialTheme.typography.h1,
        color = if (isOnPrimary) MaterialTheme.colors.background else MaterialTheme.colors.primary,
        modifier = modifier.widthIn(100.dp, 300.dp)
    )
}

@Composable
fun FindLocationTextField(
    modifier: Modifier
) {
    var userInput by rememberSaveable { mutableStateOf("") }

    TextField(
        value = userInput,
        onValueChange = { userInput = it },
        label = { Text(text = "Find a location") },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            Icon(
                painterResource(id = R.drawable.magnifying_glass), null, tint = Yellow)
        },
        modifier = modifier.fillMaxWidth(),
    )
}
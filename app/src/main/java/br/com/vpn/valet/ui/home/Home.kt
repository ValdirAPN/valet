package br.com.vpn.valet.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.vpn.valet.R
import br.com.vpn.valet.ui.theme.Primary

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
        HomeAppBar(modifier = modifier)
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier.padding(24.dp)
            ) {
                Title(title = "Let's find a parking space", modifier = modifier)
                FindLocationTextField(
                    modifier
                        .padding(top = 24.dp)
                )
            }
            Map(modifier)
        }
    }
}

@Composable
fun HomeAppBar(
    modifier: Modifier
) {
    TopAppBar(
        title = {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.valet_logo),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.background),
                        contentDescription = null
                    )
                }
        },
        modifier = modifier.fillMaxWidth(),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}

@Composable
fun Map(modifier: Modifier) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background))
}

@Composable
fun Title(
    title: String,
    modifier: Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.background,
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
            backgroundColor = MaterialTheme.colors.background
        ),
        trailingIcon = {
            Icon(
                painterResource(id = R.drawable.magnifying_glass), null, tint = Primary)
        },
        modifier = modifier.fillMaxWidth(),
    )
}
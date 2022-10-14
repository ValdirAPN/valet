package br.com.vpn.valet.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.vpn.valet.R

// Set of Material typography styles to start with
val fontFamily = FontFamily(
    Font(R.font.dmsans_regular, weight = FontWeight.Normal),
    Font(R.font.dmsans_medium, weight = FontWeight.Medium),
    Font(R.font.dmsans_bold, weight = FontWeight.Bold),
    Font(R.font.dmsans_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(R.font.dmsans_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(R.font.dmsans_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic)
)
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    h2 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
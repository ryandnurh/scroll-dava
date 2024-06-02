package com.example.task_3.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.task_3.R


val Josefins = FontFamily(
    Font(R.font.josefin_bold)
)

val JosMed = FontFamily(
    Font(R.font.josefin_med)
)


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Josefins,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Josefins,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = JosMed,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),

)
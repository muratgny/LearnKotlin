package com.example.learnkotlin.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

//This file is no need to use after material3 package. Material3 has built-in shapes.
//But If we want to change default values or add new values here, we can make it by adding
//this Shapes to the theme by adding it to the constructor od the theme in theme.kt file.
val Shapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(bottomStart = 16.dp, topEnd = 16.dp)

)
package com.example.learnkotlin

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learnkotlin.ui.theme.LearnKotlinTheme

class QuadrantActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnKotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {


                }
            }
        }
    }
}

@Composable
fun Quadrant(modifier: Modifier){
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier.weight(1f)) {//weights must be equal with each element on the same level to make them equal size
            ComposableInfoCard(
                icon = Icons.Rounded.Menu,
                title = "Main Menu",
                backgroundColor = Color(0xFFEADDFF),
                modifier = Modifier.weight(1f)
            )
            ComposableInfoCard(
                icon = Icons.Rounded.ShoppingCart,
                title = "Shopping",
                backgroundColor = Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            ComposableInfoCard(
                icon = Icons.Rounded.AccountBox,
                title = "Account",
                backgroundColor = Color(0xFFB69DF8),
                modifier = Modifier.weight(1f)
            )
            ComposableInfoCard(
                icon = Icons.Rounded.Call,
                title = "Call",
                backgroundColor = Color(0xFFF6EDFF),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ComposableInfoCard(
    icon: ImageVector,//This is one way to implement Icon as parameter, we can pass also description here as seperate
    title: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier//Note: The best practice is to provide a default Modifier parameter to all composable
// functions, which increases reusability. You should add it as the first optional parameter after all required parameters.
) {
    Column(
        modifier = modifier
            .fillMaxSize()//to make it full size
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,//these two alignment function takes the text element to the center of the column
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, "simple content")
        Text(
            text = title,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold
        )

    }
}

@Preview(showBackground = true)
@Composable
fun QuadrantPreview() {
    LearnKotlinTheme {
        Quadrant(modifier = Modifier)

    }
}
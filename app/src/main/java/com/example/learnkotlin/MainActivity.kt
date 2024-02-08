package com.example.learnkotlin

import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnkotlin.ui.theme.LearnKotlinTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnKotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    GreetingText(
                        message = getString(R.string.happy_birthday_sam),//stringResource is the new implementation instead og getString
                        longMessage = "This is a long message for you my darling to celebrate your birthday",
                        from = "From Emma",
                        modifier = Modifier.padding(8.dp)//burada padding değeri geçtik, tepeden belirlemiş olduk ama functioniçindende ayarlanabilirdi.
                    )

                }
            }
        }
    }
}


@Composable
fun GreetingImage(message: String, longMessage: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.androidparty)
    Box (modifier = Modifier.border(BorderStroke(5.dp, Color.Gray),
        shape = CutCornerShape(20.dp)
    )
    ){
        Image(
            painter = image,
            contentDescription = null,//we have add this property because it throws error without it
            contentScale = ContentScale.Crop,
            alpha = 0.5F


        )
        GreetingText(
            message = message,
            longMessage = longMessage,
            from = from,
            modifier = Modifier//ui elementlerin modifiye edilmesini saplıyor
                .fillMaxSize()
                .padding(8.dp)


        )
    }


}


@Composable
fun GreetingText(message: String, longMessage: String, from: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(1.dp),
        verticalArrangement = Arrangement.SpaceEvenly,//there are 6 different alignment type for both vertical and horizontals

    ) {
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center,
        )
        Text(
            text = longMessage,
            fontSize = 50.sp,
            lineHeight = 56.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(15.dp)
        )
        Box(modifier = Modifier
            .padding(10.dp)
            .align(alignment = Alignment.End)
            .border(
                width = 5.dp,
                brush = Brush.linearGradient(listOf(Color.Yellow, Color.Blue)),
                shape = CutCornerShape(0.dp)
            )
            .background(color = Color.Red)
            .alpha(0.3F)
        ) {
            Text(
                text = from,
                fontSize = 40.sp,
                lineHeight = 76.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(26.dp)
                    //.offset(15.dp, 45.dp)
                    //.align(alignment = Alignment.End) this align function not working because we put Text in a box. But this function works under a column
                    //.background(color = Color.Red)

                    /*.border(
                        width = 5.dp,
                        brush = Brush.linearGradient(listOf(Color.Yellow, Color.Blue)),
                        shape = CutCornerShape(5.dp),

                        )*/

            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    LearnKotlinTheme {
        GreetingImage(
            message = stringResource(R.string.happy_birthday_sam),
            longMessage = "This is a long message for you my darling to celebrate your birthday",
            from = "From Murat")

    }
}

/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(color = Color.Green) {//a piece of a layout type
        Text(
            text = "Hello $name!",
            modifier = modifier.padding(24.dp)//modifier is for the UI arrangements
        )
    }
}

@Preview(showBackground = true)//to view without running the project
@Composable
fun GreetingPreview() {
    //
    LearnKotlinTheme {
        Greeting("Murat")
    }
}*/

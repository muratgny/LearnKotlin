package com.example.learnkotlin

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.learnkotlin.ui.theme.LearnKotlinTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TıpUITests {
    @get:Rule
    val composeTestRule = createComposeRule()//it simulates as the app view

    //This is instrumentation text test that makes ui test. If emulator is working, we can see the
    // testing process
    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            LearnKotlinTheme {
                Surface (modifier = Modifier.fillMaxSize()){
                    TipTimeLayout()
                }
            }
        }
        //Here ui elements found with the text they already have with .onNodeWithText function
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput("10")//Here we are adding text to the ui element to test
        composeTestRule.onNodeWithText("Tip Percentage").performTextInput("20")
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)

        //We are writing expected text here and checking if it exists
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists(
            "No node with this text was found."
        )
    }
}



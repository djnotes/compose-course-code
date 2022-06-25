package com.example.mycomposetest

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class MyAwesomeTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun print_semanticsTree(){
        composeRule.setContent{
            MyBox()
        }

        composeRule.onRoot(useUnmergedTree = true)
            .printToLog("MyTestTag")

    }

    @Test
    fun greeting_isDisplayed(){
        composeRule.setContent{
            MyBox()
        }

        composeRule.onNodeWithText("Greet Me")
            .assertIsDisplayed()
            .performClick()

        composeRule.onNodeWithText("Welcome")
            .assertIsDisplayed()

        


    }

}
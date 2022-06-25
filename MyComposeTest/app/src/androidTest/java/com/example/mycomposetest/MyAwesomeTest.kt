package com.example.mycomposetest

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
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

}
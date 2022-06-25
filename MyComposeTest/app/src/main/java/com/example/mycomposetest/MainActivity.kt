package com.example.mycomposetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.mycomposetest.ui.theme.MyComposeTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyBox()
                }
            }
        }
    }
}


@Preview
@Composable
fun MyBox() {
    var greet by remember{mutableStateOf(false)}

    Box(Modifier
        .fillMaxSize()
    ){

        Button({
            greet = true
        }){
            Text("Greet Me")
        }
        AnimatedVisibility(greet){
            Text("Welcome", fontSize = 50.sp)
        }
    }
}
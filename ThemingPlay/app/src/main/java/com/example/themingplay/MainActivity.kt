package com.example.themingplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.themingplay.R
import com.example.themingplay.ui.theme.ThemingPlayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemingPlayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThemingPlayTheme {
        Greeting("Android")
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(contentPadding = PaddingValues(start = 16.dp)) {
                Text(stringResource(id = R.string.app_name))
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Add, null)
            }
        },
        bottomBar = {
            BottomAppBar(modifier = Modifier
                .fillMaxWidth()
                ,
                cutoutShape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
            ){
                Icon(Icons.Default.Home, null,
                    modifier = Modifier.weight(1f)
                )
                Icon(Icons.Default.AccountBox, null,
                    modifier = Modifier.weight(1f)
                )
                Icon(Icons.Default.Favorite, null,
                    modifier = Modifier.weight(1f)
                )
                Icon(Icons.Default.Settings, null,
                    modifier = Modifier.weight(1f)
                )

            }
        }
    ) {
        Box{
            Text(stringResource(R.string.welcome_to_my_app), style = MaterialTheme.typography.h2, textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    ThemingPlayTheme(darkTheme = true) {
        MainScreen()
    }
}
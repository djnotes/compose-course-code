package ninja.codezombie.jetpackcomposebutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ninja.codezombie.jetpackcomposebutton.ui.theme.JetpackComposeButtonTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Composable
fun ButtonUi(messageText: String) {
    Column (Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){

        var message by remember{mutableStateOf("")}
        Button(onClick = { message = messageText}) {
            Text("Show Message")
        }

        Text(text = message, style = MaterialTheme.typography.h2)


    }
}

@Preview
@Composable
fun ButtonUiPreview() {
//    var message by remember{mutableStateOf("Welcome to Jetpack Compose Application Development")}
    ButtonUi("Welcome to Jetpack Compose Application Development")
}
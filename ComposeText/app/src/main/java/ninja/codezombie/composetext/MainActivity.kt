package ninja.codezombie.composetext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ninja.codezombie.composetext.ui.theme.ComposeTextTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTextInput()
        }
    }
}

@Composable
fun TextUi(text: String) {
    Column{
        val customFont = Font(R.font.chango)
        val customFontFamily = FontFamily(customFont)
        BasicText(text = "Welcome", style = MaterialTheme.typography.h2.copy(fontWeight = FontWeight.Bold))
        Spacer(Modifier.height(16.dp))
        BasicText(text = text, modifier = Modifier.padding(4.dp), style = TextStyle.Default.copy(color = Color.Red, fontSize = 50.sp, fontFamily = customFontFamily))
        Text(text = text, modifier = Modifier.padding(4.dp), style = MaterialTheme.typography.h4.copy(color = Color.Green, fontSize = 60.sp, fontStyle = FontStyle.Italic))
    }
}

@Preview
@Composable
fun PreviewTextUi() {
    val textToDisplay = "Welcome To Jetpack Compose"
    TextUi(textToDisplay)
}

@Composable
fun MyTextInput() {
    Column{
        Text("BasicTextField")
        var basicText by remember{mutableStateOf("")}
        BasicTextField(value = basicText, onValueChange = {basicText = it}, Modifier.padding(8.dp))

        Text("TextField")
        var tfText by remember{mutableStateOf("")}
        TextField(value = tfText, onValueChange = {tfText = it})

        var otfText by remember{mutableStateOf("")}
        OutlinedTextField(value = otfText, onValueChange = {otfText = it}, label = {Text("Outlined")}, keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
    }
}
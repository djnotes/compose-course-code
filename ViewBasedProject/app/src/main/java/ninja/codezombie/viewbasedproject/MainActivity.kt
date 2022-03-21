package ninja.codezombie.viewbasedproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContent{
            MainScreen(message =  "This project has been upgraded to Compose")
        }
    }
}

@Composable
fun MainScreen(message: String) {
    Box(modifier = Modifier.size(300.dp).background(Color.Yellow)){
        Text(message, style = MaterialTheme.typography.h3)
    }

}


@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen(message =  "This project has been upgraded to Compose")
}
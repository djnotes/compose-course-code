package ninja.codezombie.existingandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContent{
            SimpleComposable()
        }
    }
}


@Composable
fun SimpleComposable() {
    Text("It works")
}

@Preview
@Composable
fun PreviewSimpleComposable() {
    SimpleComposable()
}
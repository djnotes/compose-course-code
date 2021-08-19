package com.example.composedemo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPictureComposable(image = R.drawable.eagle, description = "Eagle")
        }
    }
}

@Composable
fun MyPictureComposable(image: Int, description: String, modifier: Modifier = Modifier){
    Image(painter = painterResource(id = image), contentDescription = description, modifier = modifier)
}

@Preview
@Composable
fun PreviewPictureComposable(){
    MyPictureComposable(image = R.drawable.eagle, description = "Eagle", modifier = Modifier
        .background(Color.White)
        .border(5.dp, Color.Blue)
        .padding(16.dp)
        .border(5.dp, Color.Yellow, shape = RoundedCornerShape(5.dp))
        .clip(RoundedCornerShape(5.dp))

    )
}

//@Preview
//@Composable
//fun PreviewComposable2(){
//    MyPictureComposable(image = R.drawable.bird, description = "Bird")
//}
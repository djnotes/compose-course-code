package com.example.mycomposelists

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycomposelists.data.Profile
import com.example.mycomposelists.ui.theme.MyComposeListsTheme

val ProfilesList = listOf(
    Profile("John", R.drawable.john),
    Profile("Andrew", R.drawable.andrew),
    Profile("Elisa", R.drawable.elisa),
    Profile("Edward", R.drawable.edward),
    Profile("Jacky", R.drawable.jacky),
    Profile("Jade", R.drawable.jade),
    Profile("Jane", R.drawable.jane),
    Profile("Jennifer", R.drawable.jennifer),
    Profile("Jerard", R.drawable.jerard),
    Profile("Ted", R.drawable.ted),

    Profile("John", R.drawable.john),
    Profile("Andrew", R.drawable.andrew),
    Profile("Elisa", R.drawable.elisa),
    Profile("Edward", R.drawable.edward),
    Profile("Jacky", R.drawable.jacky),
    Profile("Jade", R.drawable.jade),
    Profile("Jane", R.drawable.jane),
    Profile("Jennifer", R.drawable.jennifer),
    Profile("Jerard", R.drawable.jerard),
    Profile("Ted", R.drawable.ted),

)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeListsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(Modifier
                        .fillMaxSize()
                    ) {
                        MyHorizontalList()
                        MyVerticalListUi()
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeListsTheme {
        MyVerticalListUi()
    }
}

@Composable
fun MyVerticalListUi() {
    val ctx = LocalContext.current
    LazyColumn(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        itemsIndexed(items = ProfilesList){index,profile->
            Row(modifier = Modifier
                .padding(16.dp)
                .clickable {
                    Toast
                        .makeText(ctx, "$index: ${profile.name} clicked", Toast.LENGTH_SHORT)
                        .show()
                }
            )
            {
                Text(profile.name, modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)

                    ,
                    style = MaterialTheme.typography.h4
                )
                Image(painterResource(profile.picture), profile.name, modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(100))
                    .size(150.dp)
                    ,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyHorizontalList() {
    val ctx = LocalContext.current
    LazyRow(modifier = Modifier
        .fillMaxWidth()
    ){
        items(items = ProfilesList){profile->
            Row(modifier = Modifier
                .padding(16.dp)
                .width(150.dp)
                .height(100.dp)
                .clickable {
                    Toast
                        .makeText(ctx, "${profile.name} clicked", Toast.LENGTH_SHORT)
                        .show()
                }

            ){
                Text(profile.name, modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    ,
                    style = MaterialTheme.typography.caption
                )
                Image(painterResource(profile.picture), profile.name, modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(100))
                    .size(150.dp)
                    ,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

}
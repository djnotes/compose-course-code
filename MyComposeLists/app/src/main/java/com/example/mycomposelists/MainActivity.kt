package com.example.mycomposelists

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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

    Profile("Adam", R.drawable.adam),
    Profile("Anastasia", R.drawable.anastasia),
    Profile("Chris", R.drawable.chris),
    Profile("Clara", R.drawable.clara),
    Profile("Florina", R.drawable.florina),
    Profile("Ian", R.drawable.ian),
    Profile("Richard", R.drawable.richard),
    Profile("Romain", R.drawable.romain),
    Profile("Tor", R.drawable.tor),

)

val profilesGrouped = ProfilesList.sortedBy { it.name }.groupBy { it.name[0] }

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
        Column{
            MyHorizontalList()
            MyVerticalListUi()
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyVerticalListUi() {
    val ctx = LocalContext.current
    LazyColumn(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){
//        stickyHeader {
//            Text(stringResource(id = R.string.list_of_contacts), textAlign = TextAlign.Center,
//            style = MaterialTheme.typography.h3, modifier = Modifier
//                        .fillMaxWidth()
//                    .background(Color.White)
//                        .padding(4.dp)
//            )
//        }
//
        profilesGrouped.forEach {entry->
            stickyHeader {
                Text(entry.key.toString(), style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Green)
                )
            }
            entry.value.forEach { profile->
                item {
                    Row(modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            Toast
                                .makeText(ctx, "${profile.name} clicked", Toast.LENGTH_SHORT)
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
                            .clip(RoundedCornerShape(100))
                            .size(150.dp)
                            ,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun MyHorizontalList() {
    val ctx = LocalContext.current
    LazyRow(modifier = Modifier
        .fillMaxWidth(),
        contentPadding = PaddingValues(start = 4.dp, top = 2.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(items = ProfilesList){profile->
            Image(painterResource(profile.picture), profile.name, modifier = Modifier
                .clip(RoundedCornerShape(100))
                .size(150.dp)
                ,
                contentScale = ContentScale.Crop
            )

        }
    }

}
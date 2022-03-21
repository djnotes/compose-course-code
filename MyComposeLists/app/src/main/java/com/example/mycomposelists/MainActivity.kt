package com.example.mycomposelists

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
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
import kotlinx.coroutines.launch

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
//                    Column(Modifier
//                        .fillMaxSize()
//                    ) {
//                        MyHorizontalList()
//                        MyVerticalListUi()
//                    }
//                    MyGrid()
                    MyVerticalListUi()
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

@OptIn(ExperimentalFoundationApi::class, androidx.compose.animation.ExperimentalAnimationApi::class)
@Composable
fun MyVerticalListUi() {
    val ctx = LocalContext.current
    val listState = rememberLazyListState()
    val showButton by remember{
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            AnimatedVisibility(visible = showButton){
                ExtendedFloatingActionButton(text = { Text("Jump To Top") },
                    icon = {Icon(Icons.Default.KeyboardArrowUp, null)}
                    ,onClick = {
                    scope.launch {
                        listState.animateScrollToItem(0)
                    }
                })
            }
        }
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            state = listState
        ) {

            stickyHeader {
                Text(
                    "First Visible Item Index: ${listState.firstVisibleItemIndex}\n" +
                            "First Visible Item Scroll Offset: ${listState.firstVisibleItemScrollOffset}\n" +
                            "Current Profile: ${ProfilesList[listState.firstVisibleItemIndex].name}",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Yellow)
                        .padding(4.dp)
                )
            }

            items(ProfilesList) { profile ->
                Row(modifier = Modifier
                    .background(Color.DarkGray)
                    .padding(16.dp)
                    .clickable {
                        Toast
                            .makeText(ctx, "${profile.name} clicked", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
                {
                    Text(
                        profile.name, modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h4
                    )
                    Image(
                        painterResource(profile.picture), profile.name, modifier = Modifier
                            .clip(RoundedCornerShape(100))
                            .size(150.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
//

//        profilesGrouped.forEach {entry->
//            stickyHeader {
//                Text(entry.key.toString(), style = MaterialTheme.typography.h3,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .padding(8.dp)
//                    .background(Color.Green)
//                )
//            }
//            entry.value.forEach { profile->
//                item {
//                    Row(modifier = Modifier
//                        .padding(16.dp)
//                        .clickable {
//                            Toast
//                                .makeText(ctx, "${profile.name} clicked", Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                    )
//                    {
//                        Text(profile.name, modifier = Modifier
//                            .weight(1f)
//                            .align(Alignment.CenterVertically)
//
//                            ,
//                            style = MaterialTheme.typography.h4
//                        )
//                        Image(painterResource(profile.picture), profile.name, modifier = Modifier
//                            .clip(RoundedCornerShape(100))
//                            .size(150.dp)
//                            ,
//                            contentScale = ContentScale.Crop
//                        )
//                    }
//                }
//            }
//        }

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyGrid() {
    var selectedItem by remember{mutableStateOf("")}
    LazyVerticalGrid(cells = GridCells.Adaptive(100.dp), modifier = Modifier
        .background(Color(0xFFF06292))){
        ProfilesList.forEach { profile->
            item {
                key(profile.id){
                    Image(painter = painterResource(id = profile.picture),
                        contentDescription = profile.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(100.dp)
                            .height(150.dp)
                            .scale(
                                animateFloatAsState(
                                    if (selectedItem == profile.id) 1.5f else 1.0f,
                                    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                                )
                                    .value
                            )
                            .background(Color.White)
                            .padding(2.dp)
                            .clickable {
                                selectedItem = profile.id
                            }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMyGrid() {
    MyGrid()
}
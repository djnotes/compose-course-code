package com.example.customtheming

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.customtheming.ui.theme.CustomThemingTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomThemingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.surface
                ) {
                    MyScreen()
                }
            }
        }
    }
}

@Composable
fun MyScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val (name, onNameChange) = remember{mutableStateOf("")}

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
           TopAppBar {
                Text(stringResource(R.string.app_name), modifier = Modifier
                    .padding(8.dp)
                )
           }
        },
        bottomBar = {
            BottomAppBar {
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier
                    .weight(1f)) {
                    Icon(Icons.Outlined.AccountBox, null)
                }
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier
                    .weight(1f)) {
                    Icon(Icons.Outlined.Settings, null)
                }
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier
                    .weight(1f)) {
                    Icon(Icons.Outlined.Person, null)
                }
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier
                    .weight(1f)) {
                    Icon(Icons.Outlined.FavoriteBorder, null)
                }

            }
        },
        drawerContent = {
            Text(stringResource(R.string.drawer_content), Modifier.padding(16.dp),
            style = MaterialTheme.typography.h5)

            Row(Modifier
                .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(stringResource(R.string.about_us), Modifier.padding(8.dp).weight(1f))
                Icon(Icons.Outlined.Info, null, Modifier.weight(1f))
            }

            Row(Modifier
                .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(stringResource(R.string.settings), Modifier.padding(8.dp).weight(1f))
                Icon(Icons.Outlined.Settings, null, Modifier.weight(1f))
            }

        },
        floatingActionButton = {
            ExtendedFloatingActionButton(text = {
                Text(stringResource(R.string.add_project))
            }, onClick = {
                context.startActivity(
                    Intent(Intent.ACTION_VIEW, "http://github.com/new".toUri())
                )
            },
            icon = {
                Icon(Icons.Outlined.Create, null)
            },
            modifier = Modifier
                .padding(end = 16.dp)
            )
        }

    ){padding->

        Column(modifier = Modifier
            .padding(padding)
        ){


            Text(stringResource(id = R.string.welcome_to_my_app), style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier
                .height(16.dp))

            Box(modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(1f)
                .height(50.dp)
                .border(1.dp, Color.Black, MaterialTheme.shapes.small),
                contentAlignment = Alignment.CenterStart
            ){
                BasicTextField(
                    value = name, onValueChange = onNameChange, modifier = Modifier
                        .padding(8.dp)
                )

                if(name.isBlank()){
                    Text(stringResource(R.string.enter_your_name), modifier = Modifier
                        .padding(8.dp)
                        .alpha(ContentAlpha.medium)
                    )
                }

            }


            Spacer(Modifier
                .height(16.dp)
            )

            Button(onClick = {
                scope.launch{
                    scaffoldState.snackbarHostState.showSnackbar(
                        context.getString(R.string.welcome_prefix, name)
                    )
                }

            }, modifier = Modifier
                .align(Alignment.CenterHorizontally)
            ) {
                Text(stringResource(id = R.string.lets_go), modifier = Modifier)

                Spacer(Modifier.width(8.dp))

                Icon(Icons.Filled.Face, null)

            }

        }

    }
}


@Preview(showSystemUi = true)
@Composable
fun MyScreenPreview() {
    CustomThemingTheme {
        MyScreen()
    }
}

@Preview
@Composable
fun SomePreview() {
        MyScreen()
}

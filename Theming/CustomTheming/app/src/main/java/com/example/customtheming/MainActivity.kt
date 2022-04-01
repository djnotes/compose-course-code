package com.example.customtheming

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.net.toUri
import com.example.customtheming.ui.mytheme.MyContentAlpha
import com.example.customtheming.ui.mytheme.NewTheme
import com.example.customtheming.ui.mytheme.borderColor
import com.example.customtheming.ui.theme.CustomThemingTheme
import com.example.customtheming.ui.theme.fabColor
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewTheme {
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
           MyTopAppBar {
                Text(stringResource(R.string.app_name), modifier = Modifier
                    .padding(8.dp)
                )
           }
        },
        bottomBar = {
            MyBottomAppBar{
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
                Text(stringResource(R.string.about_us),
                    Modifier
                        .padding(8.dp)
                        .weight(1f))
                Icon(Icons.Outlined.Info, null, Modifier.weight(1f))
            }

            Row(Modifier
                .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(stringResource(R.string.settings),
                    Modifier
                        .padding(8.dp)
                        .weight(1f))
                Icon(Icons.Outlined.Settings, null, Modifier.weight(1f))
            }

        },
        floatingActionButton = {
            MyExtendedFloatingActionButton(text = {
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
        },
        backgroundColor = NewTheme.colorSystem.surfaceColor,


    ){padding->
            Column(
                modifier = Modifier
                    .padding(padding)
            ) {

                MyText(
                    stringResource(id = R.string.welcome_to_my_app),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    style = NewTheme.typeSystem.big
                )

                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )

                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(1f)
                        .height(50.dp)
                        .border(1.dp, NewTheme.colorSystem.borderColor, MaterialTheme.shapes.small),
                    contentAlignment = Alignment.CenterStart
                ) {
                    BasicTextField(
                        value = name, onValueChange = onNameChange, modifier = Modifier
                            .padding(8.dp),
                        cursorBrush = SolidColor(NewTheme.colorSystem.contentColor)

                    )

                    if (name.isBlank()) {
                        MyText(
                            stringResource(R.string.enter_your_name), modifier = Modifier
                                .padding(8.dp)
                                .alpha(MyContentAlpha.medium)
                        )
                    }

                }


                Spacer(
                    Modifier
                        .height(16.dp)
                )


                MyButton(
                    onClick = {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                context.getString(R.string.welcome_prefix, name)
                            )
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)

                ) {
                    Text(stringResource(id = R.string.lets_go), modifier = Modifier)

                    Spacer(Modifier.width(8.dp))

                    Icon(Icons.Filled.Face, null)
                }


                MyCard(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(0.8f)
                        .align(Alignment.CenterHorizontally),
                    elevation = 8.dp,
                    shape = NewTheme.shapeSystem.cut
                ) {
                    Box(Modifier.fillMaxSize()) {
                        MyText(
                            stringResource(R.string.you_can_customize_or_replace_material_theme),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(16.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp
                        )
                    }


                }




            }

    }
}

@Composable
fun MyExtendedFloatingActionButton(
    text: @Composable () -> Unit,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        LocalContentAlpha provides 1f
    ) {
        ExtendedFloatingActionButton(
            text = text,
            onClick = onClick,
            icon = icon,
            modifier = modifier,
            backgroundColor = MaterialTheme.colors.fabColor,
            contentColor = NewTheme.colorSystem.contentColor
        )
    }
}

@Composable
fun MyText(text: String, modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Center, fontSize: TextUnit = TextUnit.Unspecified, style: TextStyle = NewTheme.typeSystem.medium) {
    Text(text,  modifier = modifier
        ,textAlign = textAlign,
        color = NewTheme.colorSystem.contentColor,
        fontSize = fontSize,
        style = style
    )
}


@Composable
fun MyCard(modifier: Modifier, elevation: Dp, shape: Shape, content: @Composable () -> Unit) {
    Surface(
        modifier = modifier,
        elevation = elevation,
        shape = shape,
        content = content,
        color = NewTheme.colorSystem.surfaceColor
    )
}


@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MyScreenPreview() {
    NewTheme {
        MyScreen()
    }
}


@Composable
fun MyButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content : @Composable RowScope.() -> Unit
) {
    Button(onClick = onClick,
        modifier = modifier,
    content = {
        ProvideTextStyle(
            NewTheme.typeSystem.medium
        ) { content() }
              },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = NewTheme.colorSystem.buttonColor,
            contentColor = NewTheme.colorSystem.contentColor,
            disabledBackgroundColor = NewTheme.colorSystem.contentColor.copy(
                alpha = MyContentAlpha.disabled
            )
                .compositeOver(NewTheme.colorSystem.buttonColor)

        ),
        shape = NewTheme.shapeSystem.round,
        elevation = ButtonDefaults.elevation(
            defaultElevation =  NewTheme.elevationSystem.normal,
            pressedElevation = NewTheme.elevationSystem.pressed
        ),
        enabled = false
    )
}

@Composable
fun MyTopAppBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {


    Row(modifier = modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(
            Brush.horizontalGradient(NewTheme.colorSystem.barColor)
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProvideTextStyle(
            NewTheme.typeSystem.medium.copy(
            fontFamily = NewTheme.typeSystem.defaultFontFamily
        )) {
            content()
        }
    }
}

@Composable
fun MyBottomAppBar(
    modifier: Modifier = Modifier,
content: @Composable RowScope.() -> Unit) {
    Row(modifier = modifier
        .fillMaxWidth()
        .background(Brush.horizontalGradient(NewTheme.colorSystem.barColor))
    ) {
        content()
    }

}



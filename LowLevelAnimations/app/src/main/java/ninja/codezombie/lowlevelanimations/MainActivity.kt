package ninja.codezombie.lowlevelanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ninja.codezombie.lowlevelanimations.ui.theme.LowLevelAnimationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LowLevelAnimationsTheme {
                AnimationDemo()
            }
        }
    }
}

val BOX_COLOR = Color.LightGray

@Preview
@Composable
fun AnimationDemo() {

    val infiniteTransition = rememberInfiniteTransition()
    val (infSwitch, onInfSwitchChange) =  remember{mutableStateOf(false)}

    //TODO: CREATE AN INFINITE TRANSITION
    val infiniteColor by infiniteTransition.animateColor(
        initialValue = Color.LightGray,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, 100, LinearEasing)
        )
    )


        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            ,
            
        elevation = 2.dp){
            
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .border(1.dp, Color.Black)
                    .shadow(2.dp)
                ,
            horizontalAlignment = Alignment.CenterHorizontally) {
                TopAppBar(title = {
                    Text(stringResource(R.string.app_name), Modifier
                        .padding(8.dp)
                    )

                },
                navigationIcon = {},
                actions = {})

//                Row(
//                    Modifier
//                        .fillMaxWidth()
//                        .padding(4.dp)
//                        .weight(1f)
//                    ,
//                verticalAlignment = Alignment.CenterVertically) {
//                    Button({}, Modifier
//                        .weight(1f)
//                    ) {
//                        Text(
//                            stringResource(R.string.infinite_transition),
//                            Modifier
//                                .padding(8.dp)
//                        )
//                    }
////                    Spacer(modifier = Modifier.width(8.dp))
//                    Divider(Modifier.width(8.dp))
//                    Box(
//                        Modifier
//                            .weight(2f)
//                            .background(infiniteColor)
//                            .fillMaxHeight()
//                            .clip(RoundedCornerShape(5.dp))
//                    ){
//
//                    }
//                }
//
//                Row(
//                    Modifier
//                        .fillMaxWidth()
//                        .padding(4.dp)
//                        .weight(1f)
//
//                    ,
//                    verticalAlignment = Alignment.CenterVertically
//                ){
//
//                    Button(onClick = {}, Modifier
//                        .weight(1f)) {
//                        Text(
//                            stringResource(R.string.animating_color), Modifier
//                                .padding(8.dp)
//                        )
//                    }
//                    Box(
//                        Modifier
//                            .weight(2f)
//                            .padding(4.dp)
//                            .background(BOX_COLOR)
//                            .fillMaxHeight()
//                            .clip(RoundedCornerShape(5.dp))
//                    )
//
//                }
//
//                Row(
//                    Modifier
//                        .fillMaxWidth()
//                        .padding(4.dp)
//                        .weight(1f)
//                    ,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Button({}, Modifier
//                        .weight(1f)
//                    ){
//                        Text(
//                            stringResource(R.string.animate_dp), Modifier
//                                .padding(8.dp)
//                                .weight(1f)
//                        )
//                    }
//                    Box(
//                        Modifier
//                            .weight(2f)
//                            .padding(4.dp)
//                            .background(BOX_COLOR)
//                            .fillMaxHeight()
//                            .clip(RoundedCornerShape(5.dp))
//                    ) {
//
//                    }
//
//                }
//
//                Row(
//                    Modifier
//                        .padding(4.dp)
//                        .weight(1f)
//                        .fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically
//
//                ) {
//                    Button(onClick = { /*TODO*/ }, Modifier
//                        .weight(1f)
//                    ) {
//                        Text(
//                            stringResource(R.string.animate_float), Modifier
//                                .padding(8.dp)
//                        )
//                    }
//
//
//                    Box(
//                        Modifier
//                            .weight(2f)
//                            .fillMaxHeight()
//                            .padding(4.dp)
//                            .background(BOX_COLOR)
//                            .clip(RoundedCornerShape(5.dp))
//                            ){
//
//
//                        }
//
//                }

                for (animation in MyAnims.values()){
                    val name = when(animation){
                        MyAnims.Infinite -> {stringResource(R.string.infinite_transition)}
                        MyAnims.AnimateColor -> { stringResource(R.string.animating_color)}
                        MyAnims.AnimateDp -> {stringResource(R.string.animate_dp)}
                        MyAnims.AnimateFloat -> {stringResource(R.string.animate_float)}
                        MyAnims.AnimateValue -> {stringResource(R.string.animate_value)}

                    }



                    Row(
                        Modifier
                            .padding(4.dp)
                            .weight(2f)
                            .border(2.dp, Color.Black, RoundedCornerShape(5.dp))


                        , verticalAlignment = Alignment.CenterVertically
                    ){
                        if (animation == MyAnims.Infinite){
                            Text(name, Modifier.padding(8.dp).weight(1f))
                            Switch(checked = infSwitch, onCheckedChange = onInfSwitchChange, modifier = Modifier
                                .weight(1f))
                        }
                        else{
                            Button(onClick = { /*TODO*/ }, Modifier
                                .weight(2f)
                                .padding(8.dp)
                            ) {
                                Text(name, Modifier.padding(8.dp))
                            }
                        }



                        Box(
                            Modifier
                                .padding(4.dp)
                                .weight(2f)
                                .fillMaxHeight()
                                .background(BOX_COLOR)
                                .clip(RoundedCornerShape(5.dp))
                        ){
                            when(animation){
                                MyAnims.Infinite -> {

                                }

                                MyAnims.AnimateColor -> {

                                }

                                MyAnims.AnimateFloat -> {

                                }

                                MyAnims.AnimateValue -> {

                                }

                                MyAnims.AnimateDp -> {

                                }

                            }

                        }
                    }



                }







            }
    }
}





package ninja.codezombie.composetransitions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import ninja.codezombie.composetransitions.ui.theme.ComposeTransitionsTheme

class MainActivity : ComponentActivity() {

    @ExperimentalUnitApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTransitionsTheme {
                TransitionDemo()
            }
        }
    }
}

enum class Connection{Connected, Disconnected}

@ExperimentalUnitApi
@Preview
@Composable
fun TransitionDemo() {
    var connState by remember{mutableStateOf(Connection.Disconnected)}

    val transition = updateTransition(targetState = connState, label = "Parent Transition")

    val cornerSize by transition.animateDp(
        label = "Corner Size",
        targetValueByState = { state ->
            when(state){
                Connection.Connected -> 25.dp
                else -> 0.dp
            }

        }
    )

    val textSize by transition.animateFloat(
        transitionSpec = {
            when{
                Connection.Disconnected isTransitioningTo Connection.Connected -> spring(dampingRatio = Spring.DampingRatioHighBouncy)
                else -> tween(300)
            }
                         },
        label = "Text Size",
        targetValueByState = { state ->
            when(state){
                Connection.Connected -> 50f
                else -> 30f
            }

        }
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){
        Box(modifier = Modifier
            .wrapContentSize()
            .border(2.dp, Color.Black, RoundedCornerShape(cornerSize))
            .clickable {
                connState =
                    if (connState == Connection.Connected) Connection.Disconnected else Connection.Connected
            }
            .align(Alignment.Center)
        ){
            Text(if (connState == Connection.Connected) stringResource(R.string.disconnect)
            else stringResource(R.string.connect),
                fontSize = TextUnit(textSize, TextUnitType.Sp)
                ,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}
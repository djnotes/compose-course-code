package ninja.codezombie.composegestureanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import ninja.codezombie.composegestureanimations.ui.theme.ComposeGestureAnimationsTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeGestureAnimationsTheme {
                GestureAnimationDemo()
            }
        }
    }
}

@Preview
@Composable
fun GestureAnimationDemo() {
    Column(modifier = Modifier
        .fillMaxHeight()
    ){
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(Color(0xFFE91E63))
        ) {
            val offset = remember{ Animatable(Offset(0f, 0f), Offset.VectorConverter) }

            Image(painter = painterResource(id = R.drawable.bee), contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .offset { IntOffset(offset.value.x.roundToInt(), offset.value.y.roundToInt()) }
            )

            Text(stringResource(R.string.click_in_box_to_move_bee),
            textAlign = TextAlign.Center,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopCenter)
            )




        }

        Divider(modifier = Modifier
            .height(6.dp)
            .background(Color.Black)
        )

        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(Color(0xFF03A9F4))

        ){
            Text(stringResource(R.string.swipe_to_dismiss), style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(Color.LightGray, RoundedCornerShape(5.dp))
                    .clip(RoundedCornerShape(5.dp))
                    .border(2.dp, Color.Blue, RoundedCornerShape(5.dp))

                //TODO: Add custom modifier to detect swipe and apply dismiss
            )

            Text(stringResource(R.string.drag_to_the_side_to_dismiss),
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center))

        }
    }
}
package ninja.codezombie.hybridnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ComposeScreen(onNavigate: (Int) -> Unit ) {
    Box(Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(Color.Yellow)
        .clip(RoundedCornerShape(5.dp))
        .border(2.dp, Color.Red, RoundedCornerShape(5.dp))
    ) {

        Text("Welcome To Compose Screen", style = MaterialTheme.typography.h3)

        Button({onNavigate(R.id.profileFragment)}, modifier = Modifier.align(Alignment.BottomCenter)){
            Text("Go To Settings")
        }
    }

}
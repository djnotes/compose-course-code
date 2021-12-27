package com.example.osmmap

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.osmmap.ui.theme.OSMMapTheme
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OSMMapTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Greeting("Android")
                    FunUi()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OSMMapTheme {
        Greeting("Android")
    }
}

@Composable
fun MapUi(coordinates: GeoPoint) {
    AndroidView(factory = {
        MapView(it).apply{
            Configuration.getInstance().load(it, it.getSharedPreferences("OSMMap", MODE_PRIVATE))
            setTileSource(TileSourceFactory.MAPNIK)
        }

    }, update = { mapView->
            mapView.apply{
                val controller = controller
                controller.setZoom(12.0)
                controller.animateTo(coordinates)
            }
        }
    )
}

@Composable
fun FunUi() {
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        var selectedCoordinates by remember{mutableStateOf(GeoPoint(34.053345, -118.242349))}
        SelectionUi(onShowOnMap = {requestedPoint -> selectedCoordinates = requestedPoint})
        MapUi(coordinates = selectedCoordinates)
    }
}

val municipalities = mapOf(
    Pair("San Diego", GeoPoint(32.716144, -117.162942)),
    Pair("San Francisco", GeoPoint(37.779379, -122.418433)),
    Pair("San Jose", GeoPoint(9.9445591, -84.1152506))
)

@Composable
fun SelectionUi(onShowOnMap: (GeoPoint) -> Unit) {
    Row(Modifier
        .fillMaxWidth()
    ) {
        var expanded by remember{mutableStateOf(false)}
        Text(stringResource(R.string.select_a_region_from_dropdown), Modifier
            .weight(1f)
            .padding(8.dp)
        )

        Box(Modifier
            .weight(1f)
        ){
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.Menu, null)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                for (item in municipalities){
                    DropdownMenuItem(onClick = {
                        expanded = false
                        onShowOnMap(item.value)
                    }
                    ) {
                        Text(item.key)
                    }
                }
            }
        }
    }
}


package com.example.m1w3d1navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.m1w3d1navigation.ui.theme.M1W3D1NavigationTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navCtrl = rememberNavController()
            M1W3D1NavigationTheme {

                NavHost(
                    navController = navCtrl,
                    startDestination = ScreenA
                ){
                    composable<ScreenA>{
                        Screen_A(navCtrl)
                    }

                    composable<ScreenB>{
                        val args = it.toRoute<ScreenB>()
                        Screen_B(args.value)
                    }
                }
            }
        }
    }
}



@Serializable
object ScreenA

@Serializable
data class ScreenB(
    val value: Int?
)

@Composable
fun Screen_A(navCtrl: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to the first Screen!",
            modifier = Modifier
                .background(androidx.compose.ui.graphics.Color.Cyan),
            textAlign = TextAlign.Center,
            fontSize = 23.sp,
        )

        Button(
            onClick = { navCtrl.navigate(ScreenB(value = 1))
        }
        ) {
//
            Text(
                text = "Click to go!",
                modifier = Modifier,
                textAlign = TextAlign.Start,
                fontSize = 23.sp,
                color = Color.White
            )

        }
    }

}

@Composable
fun Screen_B(value: Int?){

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        Text(
            text = "Hi,\nWelcome to the Second Screen!",
            modifier = Modifier
                .background(androidx.compose.ui.graphics.Color.Cyan),
            textAlign = TextAlign.Center,
            fontSize = 23.sp,
        )

    }

}

@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.m1w3d1navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
                        Screen_B(args.title,navCtrl)
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
    val title: String
)

@Composable
fun Screen_A(navCtrl: NavHostController) {
    Header(screenTitle = "Screen A")
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

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = { navCtrl.navigate(ScreenB(title = "Screen B"))
        }
        ) {
            Text(
                text = "Click to visit B!",
                modifier = Modifier,
                textAlign = TextAlign.Start,
                fontSize = 23.sp,
                color = Color.White
            )

        }
    }

}

@Composable
fun Screen_B(screenTitle: String, navCtrl: NavHostController){
    Header(screenTitle=screenTitle)
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

    Spacer(Modifier.height(32.dp))

        Button(
            onClick={navCtrl.navigate(ScreenA)}
        ){
            Text(
                text = "Click to visit A!",
                fontSize = 23.sp,
                color = Color.White,
            )
        }
    }
}

@Composable
fun Header(modifier: Modifier = Modifier,
           screenTitle: String
           ){
    TopAppBar(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        title = {
            Text(
                text = screenTitle,
                style = MaterialTheme.typography.titleLarge,
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "nav icon",
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),

            )
        },
        actions = {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "account icon",
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
            )

        },
    )
}










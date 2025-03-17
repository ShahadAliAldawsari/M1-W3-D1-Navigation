@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.m1w3d1navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
//                        val args = it.toRoute<ScreenA>()
                        Screen_A(screenTitle = "ScreenA", navCtrl)
                    }

                    composable<ScreenB>{
                        val args = it.toRoute<ScreenB>()
                        Screen_B(args.title,navCtrl)
                    }

                    composable<ScreenC>{
                        val args = it.toRoute<ScreenC>()
                        Screen_C(screenTitle = args.title, navCtrl = navCtrl)
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

@Serializable
data class ScreenC(
    val title: String
)

@Composable
fun Screen_A(screenTitle:String, navCtrl: NavHostController) {
    Header(screenTitle = screenTitle)
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to the $screenTitle!",
            modifier = Modifier
                .background(androidx.compose.ui.graphics.Color.Cyan),
            textAlign = TextAlign.Center,
            fontSize = 23.sp,
        )

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = {
                navCtrl.navigate(ScreenB(title = "Screen B"))
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
    //when I uncomment this line I loes all the screen content but the bottom par!!
//    myBtmBar(screenTitle = screenTitle, modifier = Modifier.padding(0.dp), navCtrl = navCtrl)
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
            text = "Hi,\nWelcome to the $screenTitle!",
            modifier = Modifier
                .background(androidx.compose.ui.graphics.Color.Cyan),
            textAlign = TextAlign.Center,
            fontSize = 23.sp,
        )

    Spacer(Modifier.height(32.dp))

        Button(
            onClick={navCtrl.navigate(route = ScreenC(title = "Screen C"))}
        ){
            Text(
                text = "Click to visit C!",
                fontSize = 23.sp,
                color = Color.White,
            )
        }
    }
}

@Composable
fun Screen_C(screenTitle: String, navCtrl: NavHostController){
    Header(screenTitle=screenTitle)
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hi,\nWelcome to the $screenTitle!",
            modifier = Modifier
                .background(Color.Cyan),
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
fun Header(modifier: Modifier = Modifier, screenTitle: String){
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
            var headerNavIcon = Icons.Filled.KeyboardArrowLeft


            if (screenTitle == "ScreenA")
                headerNavIcon = Icons.Filled.Menu

            Icon(
                imageVector = headerNavIcon,
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

@Composable
fun myBtmBar(modifier: Modifier=Modifier, screenTitle: String, navCtrl: NavHostController) {
// a good tutorial for bottom bar navigation: https://www.youtube.com/watch?v=O9csfKW3dZ4
    val navItemList = listOf(
        navItem("Back", Icons.Filled.KeyboardArrowLeft),
        navItem("", Icons.Filled.Home),
        navItem("Next", Icons.Filled.KeyboardArrowRight),
    )
    Scaffold(
//        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = true,
                        onClick = {},
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = "bottom icon")
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ){  innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
        }

    }
}

data class navItem(
    val label: String,
    val icon : ImageVector
)








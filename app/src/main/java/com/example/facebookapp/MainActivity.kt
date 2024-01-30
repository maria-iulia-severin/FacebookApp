package com.example.facebookapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.facebookapp.ui.theme.FacebookAppTheme


sealed class Destination(val route: String) {
    object Home : Destination("home")
    object Notifications : Destination("notifications")
    object Detail : Destination("detail/{itemId}") {
        fun createRoute(itemId: Int) = "detail/$itemId"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FacebookAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    FBScaffold(navController = navController)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FBScaffold(navController: NavHostController) {
    val onDrawerClicked: () -> Unit = {
    }
    Scaffold(
        bottomBar = { FBBottomNavigation(navController, onDrawerClicked) }
    ) {
        NavHost(navController = navController, startDestination = Destination.Home.route) {
            composable(Destination.Home.route) { HomeScreen() }
            composable(Destination.Notifications.route) { NotificationScreen() }
        }
    }

}
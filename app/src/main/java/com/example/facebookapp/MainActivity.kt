package com.example.facebookapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.facebookapp.ui.theme.FacebookAppTheme
import kotlinx.coroutines.launch


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

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val onDrawerClicked: () -> Unit = {
        scope.launch { drawerState.apply { if (isClosed) open() else close() } }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawer()
            }
        }) {
        Scaffold(
            bottomBar = { FBBottomNavigation(navController, onDrawerClicked) }
        ) {
            NavHost(navController = navController, startDestination = Destination.Home.route) {
                composable(Destination.Home.route) { HomeScreen(navController) }
                composable(Destination.Notifications.route) { NotificationScreen() }
            }
        }
    }
}
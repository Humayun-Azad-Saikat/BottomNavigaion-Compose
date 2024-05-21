package com.example.bottomnavigationcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationcompose.ui.theme.BottomNavigationComposeTheme


data class NavItems(
    val title:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val route:String
)

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigationComposeTheme {

                //lists
                val items = listOf(

                    NavItems(
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        route = ScreenDestination.HomeScreen.route
                    ),
                    NavItems(
                        title = "Email",
                        selectedIcon = Icons.Filled.Email,
                        unselectedIcon = Icons.Outlined.Email,
                        route = ScreenDestination.EmailScreen.route
                    ),
                    NavItems(
                        title = "Settings",
                        selectedIcon = Icons.Filled.Settings,
                        unselectedIcon = Icons.Outlined.Settings,
                        route = ScreenDestination.SettingsScreen.route
                    )

                )

                var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
                val navController = rememberNavController()

                //for selected icon (even if i press back button the icon selected autometicly
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Scaffold(
                        Modifier.background(Color.Blue),
                        bottomBar = {
                            NavigationBar(

                                //modification
                                modifier = Modifier.padding(10.dp)
                                    .clip(RoundedCornerShape(20.dp)),
                                containerColor = Color.Gray

                            ){
                                items.forEachIndexed { index, navItems ->  
                                    
                                    NavigationBarItem(
                                       // selected = selectedItemIndex == index,
                                        selected = currentDestination?.hierarchy?.any{it.route == navItems.route} == true,

                                        onClick = {
                                                  selectedItemIndex = index
                                                  navController.navigate(navItems.route){
                                                      popUpTo(navController.graph.findStartDestination().id)
                                                      launchSingleTop = true
                                                  }
                                                  },

                                        icon = {
                                            Icon(imageVector = if (index == selectedItemIndex) navItems.selectedIcon
                                                else navItems.unselectedIcon,
                                                contentDescription = navItems.title)
                                        })
                                }
                            }
                        }
                    ) {
                        NavigationHost(navController)
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomNavigationComposeTheme {
        
    }
}
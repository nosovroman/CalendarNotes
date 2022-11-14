package com.example.calendarlist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.calendarlist.common.Constants
import com.example.calendarlist.common.Screen
import com.example.calendarlist.presentation.calendar_list.CalendarListScreen
import com.example.calendarlist.presentation.detail_task.DetailTaskScreen
import com.example.calendarlist.presentation.ui.theme.CalendarListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CalendarListTheme(darkTheme = false) {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.CalendarListScreen.route
                ) {
                    composable(route = Screen.CalendarListScreen.route) {
                        CalendarListScreen(navController = navController)
                    }
                    composable(
                        route = Screen.DetailTask.route,
                        arguments = listOf(
                            navArgument(Constants.NAV_KEY__CONTENT_ID) { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        backStackEntry.arguments?.getInt(Constants.NAV_KEY__CONTENT_ID)?.let { contentId ->
                            DetailTaskScreen(contentId)
                        }
                    }
                }
            }
        }
    }
}
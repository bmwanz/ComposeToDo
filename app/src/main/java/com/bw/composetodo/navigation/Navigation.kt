package com.bw.composetodo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bw.composetodo.navigation.destinations.listComposable
import com.bw.composetodo.navigation.destinations.taskComposable
import com.bw.composetodo.util.Constants.LIST_SCREEN

@Composable
fun SetupNavigation(
    navController: NavHostController
) {
    // save backstack of composable throughout application
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    // navigation graph
    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = screen.task
        )
        taskComposable (
            navigateToListScreen = screen.list
        )
    }
}
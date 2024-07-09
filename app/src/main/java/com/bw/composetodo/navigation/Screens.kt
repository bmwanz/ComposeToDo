package com.bw.composetodo.navigation

import androidx.navigation.NavHostController
import com.bw.composetodo.util.Action
import com.bw.composetodo.util.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {

    // take action, passed from task composable, navigate to list composable
    // argument to list composable
    val list: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action.name}") {
            popUpTo(LIST_SCREEN) {
                // remove task composable from backstack
                inclusive = true
            }
        }
    }

    // list -> task, pass ID
    // request task from db
    val task: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task/$taskId")
    }
}
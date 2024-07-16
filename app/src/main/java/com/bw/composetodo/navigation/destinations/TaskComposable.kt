package com.bw.composetodo.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bw.composetodo.util.Action
import com.bw.composetodo.util.Constants.TASK_ARGUMENT_KEY
import com.bw.composetodo.util.Constants.TASK_SCREEN

// extension function
fun NavGraphBuilder.taskComposable(
    // lambda containing action for task composable
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)

    }
}
package com.bw.composetodo.navigation.destinations

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.bw.composetodo.ui.screens.list.ListScreen
import com.bw.composetodo.ui.viewmodels.SharedViewModel
import com.bw.composetodo.util.Constants.LIST_ARGUMENT_KEY
import com.bw.composetodo.util.Constants.LIST_SCREEN
import com.bw.composetodo.util.toAction

@ExperimentalMaterialApi
// extension function
fun NavGraphBuilder.listComposable(
    // lambda containing int for list composable
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            /** received from task composable as a string */
            type = NavType.StringType
        })
    ) { navBackStackEntry ->
        /** convert string received from task composable into ACTION */
        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()

        /** whenever action changes (whenever send new action to list composable,
            update in sharedviewmodel,
            also observed in ListScreen
         */
        LaunchedEffect(key1 = action) {
            sharedViewModel.action.value = action
        }

        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }
}
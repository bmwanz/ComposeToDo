package com.bw.composetodo.navigation.destinations

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.bw.composetodo.ui.screens.list.ListScreen
import com.bw.composetodo.ui.viewmodels.SharedViewModel
import com.bw.composetodo.util.Constants.LIST_ARGUMENT_KEY
import com.bw.composetodo.util.Constants.LIST_SCREEN

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
            type = NavType.StringType
        })
    ) {
        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }
}
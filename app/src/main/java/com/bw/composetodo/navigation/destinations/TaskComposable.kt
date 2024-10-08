package com.bw.composetodo.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bw.composetodo.ui.screens.task.TaskScreen
import com.bw.composetodo.ui.viewmodels.SharedViewModel
import com.bw.composetodo.util.Action
import com.bw.composetodo.util.Constants.TASK_ARGUMENT_KEY
import com.bw.composetodo.util.Constants.TASK_SCREEN

// extension function
fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    // lambda containing action for task composable
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) {
        /**
            every time we navigate from list screen -> task screen, receive task ID
            use task ID to request a ToDoTask from database
            if ID is NOT (-1), get task with specified ID, task screen recompose with new value
                Pass to TaskScreen -> TaskAppBar
                use to decide if NewTaskAppBar or ExistingTaskAppBar
            if is (-1), then from floating action button
         */

            navBackStackEntry ->
        /** android dev documentation: shouldn't put whole objects into arguments */
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)

        /** shouldn't call functions that change state of app directly in composable, call in a LaunchedEffect */
        LaunchedEffect(key1 = taskId) {
            sharedViewModel.getSelectedTask(taskId = taskId)
        }

        /**
        observe value,
        allow task screen be recomposed with new value
         */
        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        // whenever selectedTask above changes, launched effect triggers
        // use selectedTask - race condition, view may retain previous task
        LaunchedEffect(key1 = selectedTask) {
            // do not update task fields if deleted
            // cause bug for undo deleted task
            if (selectedTask != null || taskId == -1) {
                sharedViewModel.updateTaskFields(selectedTask)
            }
        }
        
        TaskScreen(
            selectedTask = selectedTask,
            sharedViewModel = sharedViewModel,
            navigateToListScreen = navigateToListScreen
        )
    }
}
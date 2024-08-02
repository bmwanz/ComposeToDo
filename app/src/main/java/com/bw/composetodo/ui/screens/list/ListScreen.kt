package com.bw.composetodo.ui.screens.list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.bw.composetodo.R
import com.bw.composetodo.ui.theme.fabBackgroundColor
import com.bw.composetodo.ui.viewmodels.SharedViewModel
import com.bw.composetodo.util.Action
import com.bw.composetodo.util.SearchAppBarState
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {

    // only trigger first time
    LaunchedEffect(key1 = true) {
        // trigger repository
        sharedViewModel.getAllTasks()
    }

    /** observe changes to sharedViewModelAction, changed in ListComposable LaunchEffect,
        pass into `handleDatabaseActions`
      */
    val action by sharedViewModel.action

    // collects value from state flow, updates whenever there is change in database
    // basically observing database
    // `by` to transfer state into list of ToDoTasks
    val allTasks by sharedViewModel.allTasks.collectAsState()

    // observe appBarState from sharedViewModel
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    val scaffoldState = rememberScaffoldState()

//    sharedViewModel.handleDatabaseActions(action = action)
    DisplaySnackBar(
        scaffoldState = scaffoldState,
        handleDatabaseActions = { sharedViewModel.handleDatabaseActions(action = action) },
        taskTitle = sharedViewModel.title.value,
        action = action
    )

    Scaffold(
        scaffoldState = scaffoldState,  // needed to display snackbar
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            /**
                https://stackoverflow.com/questions/72084865/content-padding-parameter-it-is-not-used
             */
            it
            ListContent(
                tasks = allTasks,
                navigateToTaskScreen = navigateToTaskScreen
            )
        },
        floatingActionButton = {
            ListFab(
                onFabClicked = navigateToTaskScreen
            )
        }

    )
}

@Composable
fun ListFab(
    onFabClicked: (taskId: Int) -> Unit
) {
    FloatingActionButton(
        onClick = {
            // navigate to task composable
            // not opening any existing task, -1
            // creating new task
            onFabClicked(-1)
        },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            // Add represents + symbol
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(
                id = R.string.add_button
            ),
            tint = Color.White
        )
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    handleDatabaseActions: () -> Unit,
    taskTitle: String,
    action: Action
) {
    /** sharedViewModel.handleDatabaseActions that gets passed in */
    handleDatabaseActions()

    val scope = rememberCoroutineScope()

    // whenever action changes, display snackbar
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = "${action.name}: $taskTitle",
                    actionLabel = "OK"
                )
            }
        }
    }
}

//@Composable
//@Preview
//private fun ListScreenPreview() {
//    // preview, value not important
//    ListScreen(navigateToTaskScreen = {})
//}
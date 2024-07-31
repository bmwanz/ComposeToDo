package com.bw.composetodo.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.bw.composetodo.R
import com.bw.composetodo.data.models.Priority
import com.bw.composetodo.data.models.ToDoTask
import com.bw.composetodo.ui.viewmodels.SharedViewModel
import com.bw.composetodo.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {

    /**
        observe values in sharedViewModel,
        will trigger recompose of TaskContent
     */
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = { action ->
                    // if click close or back arrow
                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    } else {
                        if (sharedViewModel.validateFields()) {
                            navigateToListScreen(action)
                        } else {
                            displayToast(context = context)
                        }
                    }
                }
            )
        },
        content = {
            /**
            https://stackoverflow.com/questions/72084865/content-padding-parameter-it-is-not-used
             */
            it
            TaskContent(
                title = title,
                onTitleChange = {
                    // update from TaskContent's onValueChanged
                    // will not update if length hits 20+ characters
                    sharedViewModel.updateTitle(it)
                },
                description = description,
                onDescriptionChange = {
                    // update from TaskContent's onValueChanged
                    sharedViewModel.description.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    // update from TaskContent's onValueChanged
                    sharedViewModel.priority.value = it
                }
            )
        }
    )
}

fun displayToast(context: Context) {
    Toast.makeText(
        context,
        context.getString(R.string.fields_empty),
        Toast.LENGTH_SHORT
    ).show()
}
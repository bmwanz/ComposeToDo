package com.bw.composetodo.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen
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
                    sharedViewModel.title.value = it
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
package com.bw.composetodo.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.bw.composetodo.util.Action

@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit
) {

    Scaffold(
        topBar = {
            TaskAppBar(navigateToListScreen = navigateToListScreen )
        },
        content = {
            /**
            https://stackoverflow.com/questions/72084865/content-padding-parameter-it-is-not-used
             */
            it
        }
    )

}
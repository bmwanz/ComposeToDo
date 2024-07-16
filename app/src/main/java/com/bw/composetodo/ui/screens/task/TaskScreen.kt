package com.bw.composetodo.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun TaskScreen() {

    Scaffold(
        topBar = {

        },
        content = {
            /**
            https://stackoverflow.com/questions/72084865/content-padding-parameter-it-is-not-used
             */
            it
        }
    )

}
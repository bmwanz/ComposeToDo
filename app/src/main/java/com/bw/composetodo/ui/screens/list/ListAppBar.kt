package com.bw.composetodo.ui.screens.list

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun ListAppBar() {

}

@Composable
fun DefaultListAppBar() {
    TopAppBar(
        title = {
            Text(text = "Tasks")
        }
    )
}
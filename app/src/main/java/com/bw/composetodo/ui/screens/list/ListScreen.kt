package com.bw.composetodo.ui.screens.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bw.composetodo.R
import com.bw.composetodo.ui.theme.fabBackgroundColor

@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Scaffold(
        topBar = {
            ListAppBar()
        },
        content = {
            // temporary to satisfy padding
                paddingValues ->
            Text(
                text = "",
                Modifier.padding(paddingValues)
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
            // not selecting any task, -1
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
@Preview
private fun ListScreenPreview() {
    // preview, value not important
    ListScreen(navigateToTaskScreen = {})
}
package com.bw.composetodo.util

enum class Action {
    /**
        pass action from task composable to list screen/composable
     */

    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    UNDO,
    NO_ACTION

}

fun String?.toAction(): Action {
    return when {
        this == "ADD" -> {
            Action.ADD
        }
        this == "UPDATE" -> {
            Action.UPDATE
        }
        this == "DELETE" -> {
            Action.DELETE
        }
        this == "DELETE_ALL" -> {
            Action.DELETE_ALL
        }
        this == "UNDO" -> {
            Action.UNDO
        }
        else -> {
            Action.NO_ACTION
        }
    }
}
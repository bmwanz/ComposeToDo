package com.bw.composetodo.data.models

import androidx.compose.ui.graphics.Color
import com.bw.composetodo.ui.theme.HighPriorityColor
import com.bw.composetodo.ui.theme.LowPriorityColor
import com.bw.composetodo.ui.theme.MediumPriorityColor
import com.bw.composetodo.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}
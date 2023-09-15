package com.tunahankaryagdi.b_log.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.b_log.R


@Composable
fun CustomOutlinedButton(
    modifier : Modifier = Modifier,
    onClick: ()->Unit,
    text: String
) {
    OutlinedButton(
        modifier = modifier,
        border = BorderStroke(width = 2.dp, MaterialTheme.colorScheme.primary),
        onClick = onClick,
    ) {
        Text(text = text,style = MaterialTheme.typography.titleMedium)
    }
}
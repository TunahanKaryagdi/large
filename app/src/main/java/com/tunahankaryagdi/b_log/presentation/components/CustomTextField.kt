package com.tunahankaryagdi.b_log.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.tunahankaryagdi.b_log.R


@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    singleLine : Boolean = true,
    label: String,
    value : String,
    icon : ImageVector,
    onValueChange: (String)->Unit
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine= singleLine,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = stringResource(id = R.string.iconsof,label))
        },
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        modifier = modifier
    )
}
package com.tunahankaryagdi.b_log.presentation.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.presentation.components.CustomButton
import com.tunahankaryagdi.b_log.presentation.components.CustomTextField
import com.tunahankaryagdi.b_log.presentation.components.SpacerHeight
import com.tunahankaryagdi.b_log.presentation.login.LoginScreen
import com.tunahankaryagdi.b_log.utils.Paddings


@Composable
fun SignupScreenRoute() {
    SignupScreen()
}



@Composable
fun SignupScreen(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Paddings.mediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.signup),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )
        SpacerHeight(dp = Paddings.mediumPadding)

        CustomTextField(
            label = stringResource(id = R.string.name),
            value = "",
            icon = Icons.Default.Edit,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )

        SpacerHeight(dp = Paddings.smallPadding)

        CustomTextField(
            label = stringResource(id = R.string.surname),
            value = "",
            icon = Icons.Default.Person,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()

        )

        SpacerHeight(dp = Paddings.smallPadding)

        CustomTextField(
            label = stringResource(id = R.string.email),
            value = "",
            icon = Icons.Default.Email,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()

        )
        SpacerHeight(dp = Paddings.smallPadding)

        CustomTextField(
            label = stringResource(id = R.string.password),
            value = "",
            icon = Icons.Default.Lock,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()

        )

        SpacerHeight(dp = Paddings.mediumPadding)

        CustomButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.signup),
            onClick = {}
        )

    }

}
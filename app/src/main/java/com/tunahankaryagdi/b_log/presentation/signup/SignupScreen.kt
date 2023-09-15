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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.presentation.components.CustomPrimaryButton
import com.tunahankaryagdi.b_log.presentation.components.CustomTextField
import com.tunahankaryagdi.b_log.presentation.components.SpacerHeight
import com.tunahankaryagdi.b_log.presentation.utils.Paddings


@Composable
fun SignupScreenRoute(
    modifier: Modifier = Modifier,
    navigateToLogin: ()->Unit,
    viewModel: SignupViewModel = hiltViewModel()
) {

    val uiState : SignupUiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = viewModel.uiState){
        viewModel.uiState.collect{
            if (it.navigateToLogin){
                navigateToLogin()
            }
        }
    }

    SignupScreen(
        modifier = modifier,
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
        onSurnameChange = viewModel::onSurnameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onClickSignup = viewModel::signup
    )
}



@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    uiState: SignupUiState,
    onNameChange: (String)->Unit,
    onSurnameChange: (String)->Unit,
    onEmailChange: (String)->Unit,
    onPasswordChange: (String)->Unit,
    onClickSignup: ()->Unit
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
            value = uiState.name,
            icon = Icons.Default.Edit,
            onValueChange = onNameChange,
            modifier = Modifier.fillMaxWidth()
        )

        SpacerHeight(dp = Paddings.smallPadding)

        CustomTextField(
            label = stringResource(id = R.string.surname),
            value = uiState.surname,
            icon = Icons.Default.Person,
            onValueChange = onSurnameChange,
            modifier = Modifier.fillMaxWidth()

        )

        SpacerHeight(dp = Paddings.smallPadding)

        CustomTextField(
            label = stringResource(id = R.string.email),
            value = uiState.email,
            icon = Icons.Default.Email,
            onValueChange = onEmailChange,
            modifier = Modifier.fillMaxWidth()

        )
        SpacerHeight(dp = Paddings.smallPadding)

        CustomTextField(
            label = stringResource(id = R.string.password),
            value = uiState.password,
            icon = Icons.Default.Lock,
            onValueChange = onPasswordChange,
            modifier = Modifier.fillMaxWidth()

        )

        SpacerHeight(dp = Paddings.mediumPadding)

        CustomPrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = if (!uiState.isLoading) stringResource(id = R.string.signup) else stringResource(id = R.string.loading),
            onClick = onClickSignup
        )

    }

}
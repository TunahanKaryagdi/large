package com.tunahankaryagdi.b_log.presentation.edit_profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.presentation.components.CustomCircularIndicator
import com.tunahankaryagdi.b_log.presentation.components.CustomErrorMessage
import com.tunahankaryagdi.b_log.presentation.components.CustomPrimaryButton
import com.tunahankaryagdi.b_log.presentation.components.CustomTextField
import com.tunahankaryagdi.b_log.presentation.components.CustomTopAppBar
import com.tunahankaryagdi.b_log.presentation.components.SpacerHeight
import com.tunahankaryagdi.b_log.presentation.utils.Paddings


@Composable
fun EditProfileScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: EditProfileViewModel = hiltViewModel()
) {

    val uiState : EditProfileUiState by viewModel.uiState.collectAsStateWithLifecycle()

    EditProfileScreen(
        modifier = modifier,
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
        onSurnameChange = viewModel::onSurnameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onClickSave = viewModel::onClickSave

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    uiState: EditProfileUiState,
    onNameChange: (String) -> Unit,
    onSurnameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onClickSave: () -> Unit
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppBar(
                navigationIcon = {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.back))
                },
                title = {
                    Text(text = stringResource(id = R.string.edit_profile), )
                }
            )
        }
    ) {
        EditProfileScreenContent(
            modifier = Modifier.padding(it),
            uiState = uiState,
            onNameChange = onNameChange,
            onSurnameChange = onSurnameChange,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onClickSave = onClickSave
        )
    }
}


@Composable
fun EditProfileScreenContent(
    modifier: Modifier = Modifier,
    uiState: EditProfileUiState,
    onNameChange: (String)->Unit,
    onSurnameChange: (String)->Unit,
    onEmailChange: (String)->Unit,
    onPasswordChange: (String)->Unit,
    onClickSave: () -> Unit
) {

    if (uiState.isLoading){
        CustomCircularIndicator()
    }
    if (uiState.error.isNotBlank()){
        CustomErrorMessage(message = uiState.error)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Paddings.smallPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SpacerHeight(Paddings.mediumPadding)
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(id = R.string.name),
            value = uiState.name,
            icon = Icons.Default.Edit,
            onValueChange = onNameChange
        )
        SpacerHeight(Paddings.smallPadding)
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(id = R.string.surname),
            value = uiState.surname,
            icon = Icons.Default.Person,
            onValueChange = onSurnameChange
        )
        SpacerHeight(Paddings.smallPadding)
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(id = R.string.email),
            value = uiState.email,
            icon = Icons.Default.Email,
            onValueChange = onEmailChange
        )
        SpacerHeight(Paddings.smallPadding)
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(id = R.string.password),
            value = "fad",
            icon = Icons.Default.Lock,
            onValueChange = onPasswordChange
        )
        SpacerHeight(Paddings.smallPadding)

        CustomPrimaryButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.save),
            onClick = onClickSave
        )

    }

}
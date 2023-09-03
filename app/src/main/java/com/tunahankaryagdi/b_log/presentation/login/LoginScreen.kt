package com.tunahankaryagdi.b_log.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.presentation.components.CustomButton
import com.tunahankaryagdi.b_log.presentation.components.CustomTextField
import com.tunahankaryagdi.b_log.presentation.components.SpacerHeight
import com.tunahankaryagdi.b_log.presentation.components.SpacerWidth
import com.tunahankaryagdi.b_log.utils.Paddings


@Composable
fun LoginScreenRoute(
    modifier: Modifier = Modifier,
    navigateToSignup : () -> Unit,
    navigateToHome : ()-> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val uiState : LoginUiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = viewModel.uiState ){
        viewModel.uiState.collect{
            if (it.navigateToMain){
                navigateToHome()
            }
        }
    }


    LoginScreen(
        navigateToSignup = navigateToSignup,
        uiState = uiState,
        onClickLogin = viewModel::login,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange
    )
}



@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    navigateToSignup : () -> Unit,
    onEmailChange: (String)->Unit,
    onPasswordChange: (String)->Unit,
    onClickLogin: ()-> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Paddings.mediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

      Text(
          text = stringResource(id = R.string.login),
          style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.primary
      )

         SpacerHeight(dp = Paddings.mediumPadding)

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

        CustomButton(
            modifier = Modifier.fillMaxWidth(),
            text = if (!uiState.isLoading) stringResource(id = R.string.login) else stringResource(id = R.string.loading),
            onClick = onClickLogin
        )

        SpacerHeight(dp = Paddings.mediumPadding)

        SignupSection(
            navigateToSignup = navigateToSignup
        )
    }
}


@Composable
private fun SignupSection(
    navigateToSignup : () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(id = R.string.dont_have_acc),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        SpacerWidth(dp = 2.dp)
        Text(
            text = stringResource(id = R.string.signup),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.clickable {
                navigateToSignup()
            }
        )

    }
}








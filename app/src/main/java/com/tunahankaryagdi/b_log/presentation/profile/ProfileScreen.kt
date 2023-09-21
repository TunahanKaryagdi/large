package com.tunahankaryagdi.b_log.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.domain.model.blog.Blog
import com.tunahankaryagdi.b_log.presentation.components.CustomCircularIndicator
import com.tunahankaryagdi.b_log.presentation.components.CustomErrorMessage
import com.tunahankaryagdi.b_log.presentation.components.CustomOutlinedButton
import com.tunahankaryagdi.b_log.presentation.components.CustomTopAppBar
import com.tunahankaryagdi.b_log.presentation.components.SpacerHeight
import com.tunahankaryagdi.b_log.presentation.components.SpacerWidth
import com.tunahankaryagdi.b_log.presentation.utils.Paddings
import com.tunahankaryagdi.b_log.utils.DateHelper
import com.tunahankaryagdi.b_log.utils.ProfileScreenTabs


@Composable
fun ProfileScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToLogin: ()->Unit,
    navigateToEditProfile: () -> Unit
) {


    val uiState : ProfileUiState by viewModel.uiState.collectAsStateWithLifecycle()


    LaunchedEffect(key1 = viewModel.uiState ){
        viewModel.uiState.collect{
            if (it.navigateToLogin){
                navigateToLogin()
            }
        }
    }

    ProfileScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToEditProfile = navigateToEditProfile,
        onClickSettings = viewModel::onClickSettings,
        onClickTab = viewModel::onClickTab,
        onClickLogout = viewModel::onClickLogout,
        onClickConfirmLogout = viewModel::onClickConfirmLogout,
        onClickCancelLogout = viewModel::onClickCancelLogout,
        onDismissBottomSheet = viewModel::onDismissBottomSheet
    )
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState,
    navigateToEditProfile: () -> Unit,
    onClickSettings :()->Unit,
    onClickTab: (Int)->Unit,
    onClickLogout: () -> Unit,
    onClickConfirmLogout: () -> Unit,
    onClickCancelLogout: () -> Unit,
    onDismissBottomSheet: () -> Unit
){



    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppBar(
                actions = {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                onClickSettings()
                            },
                        imageVector = Icons.Default.Settings,
                        contentDescription = stringResource(id = R.string.settings)
                    )
                }
            )
        }
    ) {
        ProfileScreenContent(
            modifier = modifier.padding(it),
            uiState = uiState,
            navigateToEditProfile = navigateToEditProfile,
            onClickTab = onClickTab,
            onClickLogout = onClickLogout,
            onClickConfirmLogout = onClickConfirmLogout,
            onClickCancelLogout = onClickCancelLogout,
            onDismissBottomSheet = onDismissBottomSheet
        )

    }


}



@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState,
    navigateToEditProfile: ()->Unit,
    onClickTab: (Int)->Unit,
    onClickLogout: () -> Unit,
    onClickConfirmLogout: () -> Unit,
    onClickCancelLogout: () -> Unit,
    onDismissBottomSheet: () -> Unit
) {

    if (uiState.isLoading){
        CustomCircularIndicator()
    }
    if (uiState.error.isNotBlank()){
        CustomErrorMessage(message = uiState.error)
    }
    if (uiState.showBottomSheet){
        BottomSheet(
            onClickLogout = onClickLogout,
            onDismissBottomSheet = onDismissBottomSheet
        )
    }
    if (uiState.showLogoutDialog){
        LogoutDialog(
            onClickConfirmLogout = onClickConfirmLogout,
            onClickCancelLogout = onClickCancelLogout
        )
    }

    uiState.user?.let {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = Paddings.smallPadding)
        ) {
            SpacerHeight(Paddings.smallPadding)
            ProfileImageAndNameSection(name = uiState.user.firstName, surname = uiState.user.lastName, follower = 10, following =5 )
            SpacerHeight(Paddings.smallPadding)
            CustomOutlinedButton(
                modifier = Modifier
                    .align(Alignment.End),
                onClick = {
                          navigateToEditProfile()
                },
                text = stringResource(id = R.string.edit_profile)
            )
            SpacerHeight(Paddings.smallPadding)
            TabRow(selectedTabIndex = uiState.selectedTabIndex , onClickTab = onClickTab)

            when(uiState.selectedTabIndex){
                0 ->{
                    BlogOfUserSection(
                        uiState = uiState
                    )
                }
                1 ->{

                }
                else ->{

                }
            }

        }
    }


}

@Composable
fun BlogOfUserSection(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState,

) {

    if (uiState.usersBlogLoading){
        CustomCircularIndicator()
    }
    if (uiState.usersBlogError.isNotBlank()){
        CustomErrorMessage(message = uiState.usersBlogError)
    }

    uiState.usersBlog?.let {


        if (it.isEmpty()){
            CustomErrorMessage(message = stringResource(id = R.string.no_blog_yet))
        }
        else{
        Box(
            modifier = modifier
        ) {

            LazyColumn(){
                items(uiState.usersBlog.size){index->
                    if(index != uiState.usersBlog.size-1){
                        Column {
                            BlogOfUserCard(blog = uiState.usersBlog[index])
                            Divider()
                        }
                    }
                    else{
                        BlogOfUserCard(blog = uiState.usersBlog[index])
                    }
                }
            }
        }
        }

    }


}

@Composable
fun BlogOfUserCard(
    modifier: Modifier = Modifier,
    blog: Blog
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(Paddings.smallPadding)

    ){
        Column() {
            Text(text = blog.title,style = MaterialTheme.typography.titleMedium)
            Text(text =  "Updated at ${DateHelper.calculateDateDifference(blog.updatedAt)}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun ProfileImageAndNameSection(name: String, surname: String,follower: Int,following:Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .weight(1f)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = stringResource(id = R.string.profile_image)
        )
        SpacerWidth(Paddings.smallPadding)
        Column(modifier = Modifier
            .weight(3f)
        ) {
            Text(text = "$name $surname", style = MaterialTheme.typography.headlineSmall)
            Row() {
                Text(
                    text = stringResource(id = R.string.follower,follower),
                    style = MaterialTheme.typography.titleMedium 
                )
                SpacerWidth(Paddings.extraSmallPadding)
                Text(text = "x", style = MaterialTheme.typography.titleMedium)
                SpacerWidth(Paddings.extraSmallPadding)
                Text(
                    text = stringResource(id = R.string.following,following),
                    style = MaterialTheme.typography.titleMedium
                )

            }
        }
    }
}

@Composable
private fun TabRow(
    selectedTabIndex: Int,
    onClickTab: (Int) -> Unit
){
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        ProfileScreenTabs.values().mapIndexed { index, profileScreenTabs ->
            Tab(selected = selectedTabIndex == index,
                modifier = Modifier
                    .padding(Paddings.smallPadding),

                onClick = { onClickTab(index) }
            ) {
                Text(text = profileScreenTabs.name)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSheet(
    modifier: Modifier = Modifier,
    onClickLogout: () -> Unit,
    onDismissBottomSheet: ()->Unit,
) {
    ModalBottomSheet(
        onDismissRequest = { onDismissBottomSheet() },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
    ) {

        TextButton(
            onClick = {
                onClickLogout()
            },
            modifier = Modifier
                .fillMaxWidth()

        ) {
         Text(text = stringResource(id = R.string.logout))
        }
    }
}
@Composable
private fun LogoutDialog(
    modifier: Modifier = Modifier,
    onClickConfirmLogout: () -> Unit,
    onClickCancelLogout: () -> Unit
){


  AlertDialog(
      title = {
              Text(text = stringResource(id = R.string.logout))
      },
      confirmButton = {
          TextButton(onClick = { onClickConfirmLogout() }) {
              Text(text = stringResource(id = R.string.logout))
          }
      },
      dismissButton = {
          TextButton(onClick = { onClickCancelLogout()}) {
              Text(text = stringResource(id = R.string.cancel))
          }
      },
      text = {
             Text(text = stringResource(id = R.string.are_you_sure))
      },
      onDismissRequest = {
          onClickCancelLogout()
      }
  )
}




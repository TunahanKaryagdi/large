package com.tunahankaryagdi.b_log.presentation.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.domain.model.blog.Blog
import com.tunahankaryagdi.b_log.presentation.components.CustomBlogCard
import com.tunahankaryagdi.b_log.presentation.components.CustomCircularIndicator
import com.tunahankaryagdi.b_log.presentation.components.CustomErrorMessage
import com.tunahankaryagdi.b_log.presentation.components.CustomTopAppBar
import com.tunahankaryagdi.b_log.presentation.components.SpacerHeight
import com.tunahankaryagdi.b_log.presentation.utils.Paddings


@Composable
fun HomeScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToAddScreen: () -> Unit,
    navigateToDetailScreen: (String) -> Unit
) {

    val uiState : HomeUiState by viewModel.uiState.collectAsStateWithLifecycle()


    LaunchedEffect(key1 = true){
        viewModel.getSavedBlogs()
        viewModel.getBlogs()

    }
    LaunchedEffect(key1 = uiState.needRefresh){
        println("refresh need")
    }


    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToAddScreen = navigateToAddScreen,
        navigateToDetailScreen = navigateToDetailScreen,
        isSaved = viewModel::isSaved,
        onClickSaved = viewModel::onClickSaved,
        onClickUnsaved = viewModel::onClickUnsaved
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    navigateToAddScreen: ()->Unit,
    navigateToDetailScreen: (String) -> Unit,
    isSaved : (Blog)-> Boolean,
    onClickSaved :(Blog) -> Unit,
    onClickUnsaved: (Blog) -> Unit
){

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.home)) },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToAddScreen() },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(id = R.string.add))
            }
        }
    ){

        HomeScreenContent(
            modifier = modifier.padding(it),
            uiState = uiState,
            navigateToDetailScreen = navigateToDetailScreen,
            isSaved = isSaved,
            onClickSaved = onClickSaved,
            onClickUnsaved = onClickUnsaved
        )
    }

}





@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    isSaved : (Blog)-> Boolean,
    navigateToDetailScreen: (String) -> Unit,
    onClickSaved :(Blog) -> Unit,
    onClickUnsaved: (Blog) -> Unit
) {
    
    if (uiState.isLoading){
        CustomCircularIndicator()
    }
    if (uiState.error.isNotBlank()){
        CustomErrorMessage(message = uiState.error)
    }
    
    if (uiState.blogs.isEmpty() && uiState.error.isBlank()){
        CustomErrorMessage(message = stringResource(id = R.string.empty_blog_list))
    }
    

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Paddings.smallPadding)
    ){

            item {
                SpacerHeight(Paddings.smallPadding)
            }
            items(uiState.blogs.size){
                CustomBlogCard(
                    blog = uiState.blogs[it],
                    navigateToDetailScreen = navigateToDetailScreen,
                    isSaved =isSaved,
                    onClickUnsaved = onClickUnsaved,
                    onClickSaved = onClickSaved
                )
                SpacerHeight(Paddings.smallPadding)
            }
    }

}











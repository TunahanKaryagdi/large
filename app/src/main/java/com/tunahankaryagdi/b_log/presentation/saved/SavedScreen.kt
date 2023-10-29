package com.tunahankaryagdi.b_log.presentation.saved

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.domain.model.saved.SavedBlog
import com.tunahankaryagdi.b_log.presentation.components.CustomCircularIndicator
import com.tunahankaryagdi.b_log.presentation.components.CustomErrorMessage
import com.tunahankaryagdi.b_log.presentation.components.CustomSavedBlogCard
import com.tunahankaryagdi.b_log.presentation.components.CustomTopAppBar
import com.tunahankaryagdi.b_log.presentation.components.SpacerHeight
import com.tunahankaryagdi.b_log.presentation.utils.Paddings


@Composable
fun SavedScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: SavedViewModel = hiltViewModel(),
    navigateToDetailScreen: (String) -> Unit
){
    
    val uiState : SavedUiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    SavedScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToDetailScreen = navigateToDetailScreen,
        onClickUnsaved = viewModel::onClickUnsaved
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedScreen(
    modifier: Modifier = Modifier,
    uiState: SavedUiState,
    navigateToDetailScreen: (String) -> Unit,
    onClickUnsaved: (SavedBlog) -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.saved)) },
                scrollBehavior = scrollBehavior
            )
        },

    ){
        SavedScreenContent(
            modifier = modifier.padding(it),
            uiState = uiState,
            navigateToDetailScreen = navigateToDetailScreen,
            onClickUnsaved = onClickUnsaved
        )
    }
}


@Composable
fun SavedScreenContent(
    modifier: Modifier = Modifier,
    uiState: SavedUiState,
    navigateToDetailScreen : (String)-> Unit,
    onClickUnsaved : (SavedBlog)-> Unit
) {

    if (uiState.isLoading) CustomCircularIndicator()
    if (uiState.error.isNotBlank()) CustomErrorMessage(message = uiState.error)
    if (uiState.savedBlogs.isEmpty() && uiState.error.isBlank()) CustomErrorMessage(message = stringResource(
        id = R.string.no_saved_blogs
    ))
    
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Paddings.smallPadding)
    ){

        item {
            SpacerHeight(Paddings.smallPadding)
        }
        items(uiState.savedBlogs.size){
            CustomSavedBlogCard(
                savedBlog = uiState.savedBlogs[it],
                navigateToDetailScreen = navigateToDetailScreen,
                onClickUnsaved = onClickUnsaved
            )
            SpacerHeight(Paddings.smallPadding)
        }
    }
}
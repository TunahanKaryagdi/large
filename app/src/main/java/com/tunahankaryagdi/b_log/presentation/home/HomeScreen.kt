package com.tunahankaryagdi.b_log.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.domain.model.blog.Blog
import com.tunahankaryagdi.b_log.presentation.components.CustomCircularIndicator
import com.tunahankaryagdi.b_log.presentation.components.CustomErrorMessage
import com.tunahankaryagdi.b_log.presentation.components.CustomTopAppBar
import com.tunahankaryagdi.b_log.presentation.components.SpacerHeight
import com.tunahankaryagdi.b_log.presentation.components.SpacerWidth
import com.tunahankaryagdi.b_log.presentation.utils.Paddings
import com.tunahankaryagdi.b_log.utils.DateHelper


@Composable
fun HomeScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToAddScreen: () -> Unit,
    navigateToDetailScreen: (String) -> Unit
) {

    val uiState : HomeUiState by viewModel.uiState.collectAsStateWithLifecycle()


    LaunchedEffect(key1 = true,){
        viewModel.getBlogs()
    }


    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToAddScreen = navigateToAddScreen,
        navigateToDetailScreen = navigateToDetailScreen,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    navigateToAddScreen: ()->Unit,
    navigateToDetailScreen: (String) -> Unit,
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

        )
    }

}





@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    navigateToDetailScreen: (String) -> Unit,
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
                BlogCard(
                    blog = uiState.blogs[it],
                    navigateToDetailScreen = navigateToDetailScreen,
                )
                SpacerHeight(Paddings.smallPadding)
            }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogCard(
    modifier: Modifier = Modifier,
    profileImageSize: Int = 30,
    blogImageSize: Int = 80,
    blog: Blog,
    navigateToDetailScreen: (String) -> Unit,
) {


    Card(
        modifier = modifier
            .fillMaxWidth(),
        onClick = { navigateToDetailScreen(blog.id) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = Paddings.extraSmallPadding)
    ) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Paddings.smallPadding)
    ) {


        Column() {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    modifier = Modifier
                        .size(profileImageSize.dp)
                        .clip(CircleShape),

                    contentDescription = stringResource(
                        id = R.string.profile_image
                    )
                )

                SpacerWidth(Paddings.extraSmallPadding)
                Text(modifier = Modifier.weight(1f),text = "${blog.author.firstName} ${blog.author.lastName}")
                SpacerWidth(Paddings.extraSmallPadding)
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = stringResource(id = R.string.star)
                )
                
            }
            SpacerHeight(Paddings.smallPadding)
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(blog.image)
                        .crossfade(true)
                        .build(),
                    modifier = modifier
                        .size(blogImageSize.dp)
                        .weight(1f),
                    error = painterResource(id = R.drawable.ic_launcher_background),
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = stringResource(id = R.string.blog_image),
                )
                SpacerWidth(Paddings.smallPadding)
                Column(
                    modifier = modifier
                        .weight(2f)
                ) {
                    Text(
                        text = blog.title,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = DateHelper.calculateDateDifference(blog.updatedAt),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }
            SpacerHeight(Paddings.smallPadding)
            LazyRow(){
                items(blog.tags.size){
                    Text(text = blog.tags[it])
                    SpacerWidth(Paddings.extraSmallPadding)
                }
            }
        }

    }
    }
}








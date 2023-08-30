package com.tunahankaryagdi.b_log.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.data.model.Blog
import com.tunahankaryagdi.b_log.presentation.components.SpacerHeight
import com.tunahankaryagdi.b_log.presentation.components.SpacerWidth
import com.tunahankaryagdi.b_log.utils.Paddings


@Composable
fun HomeScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToAddScreen: () -> Unit
) {

    val uiState : HomeUiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToAddScreen = navigateToAddScreen
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    navigateToAddScreen: ()->Unit,
){

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToAddScreen() },
                containerColor = MaterialTheme.colorScheme.onSecondary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ){

        HomeScreenContent(
            modifier = modifier.padding(it),
            uiState = uiState
        )
    }

}





@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
) {
    if (uiState.blogs.isEmpty()){
        EmptyContent()
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
                BlogCard(blog = uiState.blogs[it])
                SpacerHeight(Paddings.smallPadding)
            }


    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogCard(
    modifier :Modifier = Modifier,
    blog: Blog,
    screenWidth : Int = LocalConfiguration.current.screenWidthDp,
    screenHeight : Int = LocalConfiguration.current.screenHeightDp
) {



    Card(
        modifier = modifier
            .fillMaxWidth(),
        onClick = { },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = Paddings.smallPadding)

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {

           Image(
               painter = painterResource(id = R.drawable.ic_launcher_background),
               contentDescription = blog.title,
               contentScale = ContentScale.FillWidth,
               modifier = Modifier
                   .fillMaxWidth()
                   .height((screenHeight / 7f).dp)
           )
            SpacerHeight(Paddings.smallPadding)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Paddings.smallPadding)
            ){
                Text(
                    text = blog.tags[0],
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                SpacerHeight(Paddings.smallPadding)
                Text(
                    text = blog.title,
                    style = MaterialTheme.typography.titleMedium
                )
                SpacerHeight(Paddings.smallPadding)

                Row(
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size((screenWidth / 10f).dp)
                            .clip(CircleShape)                       // clip to the circle shape
                            .border(2.dp, Color.Gray, CircleShape)
                    )
                    SpacerWidth(Paddings.smallPadding)
                    Text(
                        text = "Name Surname",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    SpacerWidth(Paddings.smallPadding)
                    Text(
                        text = "x",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = blog.updatedAt,
                        style = MaterialTheme.typography.bodyMedium

                    )
                }
            }

            SpacerHeight(Paddings.smallPadding)





        }

    }
}

@Composable
private fun EmptyContent(modifier: Modifier= Modifier){
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = stringResource(id = R.string.empty_list), style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold))
    }
}


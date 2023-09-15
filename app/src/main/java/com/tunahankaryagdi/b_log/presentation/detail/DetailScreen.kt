package com.tunahankaryagdi.b_log.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.domain.model.BlogDetail
import com.tunahankaryagdi.b_log.presentation.components.CustomCircularIndicator
import com.tunahankaryagdi.b_log.presentation.components.CustomErrorMessage
import com.tunahankaryagdi.b_log.presentation.components.SpacerHeight
import com.tunahankaryagdi.b_log.presentation.components.SpacerWidth
import com.tunahankaryagdi.b_log.presentation.home.HomeUiState
import com.tunahankaryagdi.b_log.presentation.utils.Paddings
import com.tunahankaryagdi.b_log.utils.DateHelper
import com.tunahankaryagdi.b_log.utils.SectionTypes


@Composable
fun DetailScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel()
) {


    val uiState : DetailUiState by viewModel.uiState.collectAsStateWithLifecycle()


    DetailScreen(
        uiState = uiState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailUiState
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = modifier
                    .padding(horizontal = Paddings.smallPadding),
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                navigationIcon = {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.back))
                }
            )
        }
    ) {
        DetailScreenContent(
            modifier = Modifier
                .padding(it),
            uiState = uiState
        )

    }


}


@Composable
fun DetailScreenContent(
    modifier: Modifier = Modifier,
    uiState: DetailUiState,
    blogImageSize: Int  = 175
) {

    val blogDetail = uiState.blogDetail

    if (uiState.isLoading){
        CustomCircularIndicator()
    }
    if (uiState.error.isNotBlank()){
        CustomErrorMessage(message = uiState.error)
    }

    blogDetail?.let {

        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = Paddings.smallPadding)
        ){

            item {
                Text(
                    text = blogDetail.title,
                    style = MaterialTheme.typography.headlineLarge.copy(color = MaterialTheme.colorScheme.primary)
                )
                SpacerHeight(Paddings.smallPadding)
                UserSection(
                    authorName = "${blogDetail.author.firstName} ${blogDetail.author.lastName}",
                    updatedAt = DateHelper.calculateDateDifference(blogDetail.updatedAt)
                )
                SpacerHeight(Paddings.smallPadding)
                Image(
                    modifier = Modifier
                        .height(blogImageSize.dp)
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = stringResource(id = R.string.blog_image)
                )
                SpacerHeight(Paddings.smallPadding)
            }
            items(blogDetail.sections.size){

                when(blogDetail.sections[it].type){

                    SectionTypes.TEXT.name->{
                        Text(
                            text = blogDetail.sections[it].content,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    SectionTypes.TITLE_TEXT.name->{
                        Text(
                            text = blogDetail.sections[it].title,
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = blogDetail.sections[it].content,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    SectionTypes.CODE.name->{
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.onSecondary, RectangleShape)
                                .padding(Paddings.smallPadding)
                        ) {
                            Text(
                                text = blogDetail.sections[it].content,
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
                    }
                    SectionTypes.LINK.name->{
                        val url = blogDetail.sections[it].content
                        val text  = buildAnnotatedString {
                            append("URL: ")
                            withStyle(style = SpanStyle(color = Color.Cyan), ) {
                                pushStringAnnotation(tag = "URL", annotation = url )
                                append(url)
                            }
                        }
                        ClickableText(
                            text = text ,
                            onClick = {},
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    SectionTypes.IMAGE.name->{

                    }


                }

            }

        }
    }



}


@Composable
private fun UserSection(
    modifier: Modifier = Modifier,
    authorName: String,
    updatedAt: String,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .weight(1f)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = stringResource(
                id = R.string.profile_image
             )
        )
        SpacerWidth(Paddings.smallPadding)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .weight(5f)
        ){
            Column(
                horizontalAlignment = Alignment.Start
            ){
                
                Text(
                    text = authorName,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                
                Text(
                    text = stringResource(id = R.string.updated_at,updatedAt),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            SpacerWidth(Paddings.smallPadding)
            Text(
                text = stringResource(id = R.string.follow),
                style = MaterialTheme.typography.titleMedium
            )
        }

    }

}
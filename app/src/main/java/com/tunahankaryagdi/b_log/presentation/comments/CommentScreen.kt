package com.tunahankaryagdi.b_log.presentation.comments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.domain.model.comment.Comment
import com.tunahankaryagdi.b_log.presentation.components.CustomCircularIndicator
import com.tunahankaryagdi.b_log.presentation.components.CustomErrorMessage
import com.tunahankaryagdi.b_log.presentation.components.CustomTopAppBar
import com.tunahankaryagdi.b_log.presentation.components.SpacerHeight
import com.tunahankaryagdi.b_log.presentation.components.SpacerWidth
import com.tunahankaryagdi.b_log.presentation.utils.Paddings
import com.tunahankaryagdi.b_log.utils.DateHelper


@Composable
fun CommentsScreenRoute(
    modifier : Modifier = Modifier,
    viewModel: CommentViewModel = hiltViewModel()
) {

    val uiState : CommentUiState by viewModel.uiState.collectAsStateWithLifecycle()

    CommentsScreen(
        modifier = modifier,
        uiState = uiState,
        onCommentChange = viewModel::onCommentChange,
        onClickPostComment = viewModel::onClickPostComment
    )
}

@Composable
fun CommentsScreen(
    modifier: Modifier = Modifier,
    uiState: CommentUiState,
    onCommentChange: (String) -> Unit,
    onClickPostComment: () -> Unit
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }

            )
        }
    ){
        CommentsScreenContent(
            modifier = Modifier.padding(it),
            uiState = uiState,
            onCommentChange = onCommentChange,
            onClickPostComment = onClickPostComment
        )
    }


}

@Composable
fun CommentsScreenContent(
    modifier: Modifier = Modifier,
    uiState: CommentUiState,
    onCommentChange: (String)->Unit,
    onClickPostComment: () -> Unit
) {


    if (uiState.isLoading){
        CustomCircularIndicator()
    }

    if (uiState.error.isNotBlank()){
        CustomErrorMessage(message = uiState.error)
    }


    uiState.comments?.let {commentList->
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            if (commentList.isEmpty()){
                CustomErrorMessage(message = stringResource(id = R.string.no_comment_yet))
            }
            else{
                LazyColumn(){
                    items(commentList.size){
                        if (it == commentList.size-1){
                            CommentCard(
                                comment = commentList[it]
                            )
                        }
                        else{
                            Column {
                                CommentCard(
                                    comment = commentList[it]
                                )
                                SpacerHeight(Paddings.smallPadding)
                                Divider(thickness = 1.dp)
                            }
                        }

                    }
                }
            }

            CommentAddSection(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                value = uiState.newComment,
                onValueChange = onCommentChange,
                onClickPostComment = onClickPostComment
            )
        }
    }







}


@Composable
fun CommentCard(
    modifier: Modifier = Modifier,
    profileImageSize: Int = 30,
    comment: Comment
) {
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Paddings.smallPadding)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            SpacerHeight(Paddings.smallPadding)
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    modifier = Modifier
                        .size(profileImageSize.dp)
                        .clip(CircleShape),

                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = stringResource(
                        id = R.string.profile_image
                    )
                )
                SpacerWidth(Paddings.extraSmallPadding)
                Column() {
                    Text(text = "${comment.user.firstName} ${comment.user.lastName}")
                    Text(text = DateHelper.calculateDateDifference(comment.updatedAt), style = MaterialTheme.typography.bodySmall)
                }

            }
            SpacerHeight(Paddings.smallPadding)
            Text(text = comment.content, maxLines = 4,style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
private fun CommentAddSection(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onClickPostComment: () -> Unit
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = Paddings.smallPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CommentTextField(
            modifier = Modifier
                .weight(5f),
            value = value,
            onValueChange = onValueChange,

        )
        Icon(
            modifier = Modifier
                .weight(1f)
                .clickable {
                         onClickPostComment()
                },
            imageVector = Icons.Default.Send,
            contentDescription = stringResource(id = R.string.send)
        )
    }
}

@Composable
private fun CommentTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String)->Unit
){

    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder= {
            Text(text = stringResource(id = R.string.whats_comment))
        },

        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )

}
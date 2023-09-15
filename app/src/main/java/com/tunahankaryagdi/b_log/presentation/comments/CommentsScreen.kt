package com.tunahankaryagdi.b_log.presentation.comments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.presentation.components.SpacerWidth
import com.tunahankaryagdi.b_log.presentation.utils.Paddings


@Composable
fun CommentsScreenRoute(
    modifier : Modifier = Modifier,
    viewModel: CommentsViewModel = hiltViewModel()
) {

}

@Composable
fun CommentsScreen(
    modifier: Modifier = Modifier
) {
    
}


@Composable
fun CommentCard(
    modifier: Modifier = Modifier,
    profileImageSize: Int = 30,
) {
    
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
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
                Text(text = "ali veli")

            }
            
            Text(text = "FADİKFAJKFA".repeat(10), maxLines = 4,)
        }
    }

}
package com.tunahankaryagdi.b_log.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.domain.model.blog.Blog
import com.tunahankaryagdi.b_log.presentation.utils.Paddings
import com.tunahankaryagdi.b_log.utils.DateHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBlogCard(
    modifier: Modifier = Modifier,
    profileImageSize: Int = 30,
    blogImageSize: Int = 80,
    blog: Blog,
    navigateToDetailScreen: (String) -> Unit,
    isSaved : (Blog)-> Boolean,
    onClickSaved :(Blog) -> Unit,
    onClickUnsaved: (Blog) -> Unit
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
                        modifier = Modifier
                            .clickable {
                                if (isSaved(blog)) onClickUnsaved(blog) else  onClickSaved(blog)
                            },
                        painter = if (isSaved(blog)) painterResource(id = R.drawable.ic_saved) else painterResource(id = R.drawable.ic_unsaved)  ,
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
package com.tunahankaryagdi.b_log.presentation.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.presentation.components.SpacerHeight
import com.tunahankaryagdi.b_log.presentation.components.SpacerWidth
import com.tunahankaryagdi.b_log.utils.Paddings
import com.tunahankaryagdi.b_log.utils.ProfileScreenTabs


@Composable
fun ProfileScreenRoute(
    modifier: Modifier = Modifier
) {
    ProfileScreen(
        modifier = modifier
    )
}


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {

    var selectedIndex by remember{
        mutableStateOf(0)
    }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Paddings.smallPadding)
    ) {
        SpacerHeight(Paddings.smallPadding)
        ProfileImageAndNameSection(name = "Tunahan", surname = "Karyağdı", follower = 10, following =5 )
        SpacerHeight(Paddings.smallPadding)

        OutlinedButton(
            modifier = Modifier.align(Alignment.End),
            border = BorderStroke(width = 2.dp, MaterialTheme.colorScheme.primary),
            onClick = { },
        ) {
            Text(text = stringResource(id = R.string.edit_profile),style = MaterialTheme.typography.titleMedium )
        }
        SpacerHeight(Paddings.smallPadding)

        TabRow(
            selectedTabIndex = selectedIndex,
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            ProfileScreenTabs.values().mapIndexed { index, profileScreenTabs ->
                Tab(selected = selectedIndex == index,
                    modifier = Modifier
                        .padding(Paddings.smallPadding),

                    onClick = { selectedIndex = index }
                ) {
                    Text(text = profileScreenTabs.name)
                }
            }
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


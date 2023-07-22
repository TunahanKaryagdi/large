package com.tunahankaryagdi.b_log.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.presentation.components.SpacerHeight
import com.tunahankaryagdi.b_log.presentation.components.SpacerWidth
import com.tunahankaryagdi.b_log.utils.Paddings


@Composable
fun HomeScreenRoute(
) {

    HomeScreen()
}




@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Paddings.smallPadding)
    ){


            item {
                SpacerHeight(Paddings.smallPadding)
            }
            items(3){
                BlogCard()
                SpacerHeight(Paddings.smallPadding)
            }


    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogCard(
    modifier :Modifier = Modifier,
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
               contentDescription = "Blog Image",
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
                    text = "Technology",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
                SpacerHeight(Paddings.smallPadding)
                Text(
                    text = "Architectural Engineering Wonders of the modern era for your Inspiration",
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
                        text = "21 October 2023",
                        style = MaterialTheme.typography.bodyMedium

                    )
                }
            }

            SpacerHeight(Paddings.smallPadding)





        }

    }

}





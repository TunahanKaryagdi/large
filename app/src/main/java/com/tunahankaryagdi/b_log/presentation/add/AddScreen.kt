package com.tunahankaryagdi.b_log.presentation.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.presentation.components.SpacerHeight
import com.tunahankaryagdi.b_log.utils.Paddings


@Composable
fun AddScreenRoute(
    modifier: Modifier = Modifier
) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    AddScreen(
        modifier = modifier,
        title = "",
        sectionTitle = "",
        sectionContent = "",
        onTitleValueChange = {

        },
        onSectionTitleValueChange = {

        },
        onSectionContentValueChange = {

        },
        onConfirmNewSection = {title,content->

        },

        onCancelSection ={

        } ,
        onTagClick = {str,bool->

        },
        onClickAddSection = {
            showDialog = !showDialog
        },
        showDialog = showDialog
    )
}


@Composable
fun AddScreen(
    modifier: Modifier = Modifier,
    title: String,
    sectionTitle: String,
    sectionContent: String,
    onSectionTitleValueChange: (String) -> Unit,
    onSectionContentValueChange: (String) -> Unit,
    onConfirmNewSection: (String, String) -> Unit,
    onCancelSection: () -> Unit,
    onTitleValueChange: (String) -> Unit,
    onTagClick: (String, Boolean) -> Unit,
    onClickAddSection: () -> Unit,
    showDialog :Boolean
){
    if (showDialog){

        CustomDialog(
            sectionTitle = sectionTitle ,
            sectionContent = sectionContent,
            onSectionTitleValueChange = onSectionTitleValueChange,
            onSectionContentValueChange = onSectionContentValueChange,
            onConfirmNewSection = onConfirmNewSection,
            onCancelSection = onCancelSection
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Paddings.smallPadding)
    ) {

            SpacerHeight(Paddings.smallPadding)
            TitleSection(
                value = title,
                onTitleValueChange = onTitleValueChange
            )
            SpacerHeight(Paddings.mediumPadding)
            Divider(
                thickness = 5.dp,
                color = MaterialTheme.colorScheme.primary
            )

            SpacerHeight(Paddings.smallPadding)
            BlogImageSection()
            SpacerHeight(Paddings.mediumPadding)
            Divider(
                thickness = 5.dp,
                color = MaterialTheme.colorScheme.primary
            )


            SpacerHeight(Paddings.smallPadding)
            TagsSection(
                onTagClick = onTagClick
            )
            SpacerHeight(Paddings.mediumPadding)
            Divider(
                thickness = 5.dp,
                color = MaterialTheme.colorScheme.primary
            )


            SpacerHeight(Paddings.smallPadding)
            SectionSection(
                onClickAddSection = onClickAddSection
            )

    }
}

@Composable
fun TitleSection(
    modifier: Modifier = Modifier,
    value: String,
    onTitleValueChange: (String) -> Unit
) {

    SectionsTitle(text = stringResource(id = R.string.title))
    SpacerHeight(Paddings.smallPadding)

    AddScreenTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onTitleValueChange
    )

}


@Composable
fun BlogImageSection(
    modifier: Modifier = Modifier,
){
    SectionsTitle(text = stringResource(id = R.string.blog_image))
    SpacerHeight(Paddings.smallPadding)
    CircleImage(widthDivisionTo = 4)
}


@Composable
fun TagsSection(
    modifier: Modifier = Modifier,
    onTagClick: (String,Boolean) -> Unit
) {

    val a = listOf("technology","social","art")
    SectionsTitle(text = stringResource(id = R.string.tags))
    SpacerHeight(Paddings.smallPadding)
    LazyRow(){
        items(a.size) {
            SelectableTag(
                text = a[it],
                onClick = onTagClick
            )
        }
    }

}

@Composable
fun SelectableTag(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (String,Boolean) -> Unit
){
    var isSelected by remember {
        mutableStateOf(false)
    }
    Button(

        modifier = modifier
            .wrapContentSize()
            .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
        onClick = {
            isSelected = !isSelected
            onClick(text,isSelected)
        },
        colors = ButtonDefaults
            .buttonColors(
                containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background,
                contentColor = if (isSelected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primary
            )

        ) {
        Text(text = text)
    }

}

@Composable
fun SectionSection(
    modifier: Modifier = Modifier,
    onClickAddSection: () ->Unit
) {

    var sectionCount by remember{
        mutableStateOf(0)
    }


    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()

    ) {
        SectionsTitle(text = stringResource(id = R.string.sections))
        IconButton(
            onClick =  onClickAddSection
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add new section")
        }
    }
    SpacerHeight(Paddings.smallPadding)
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Paddings.smallPadding),
        horizontalAlignment = Alignment.Start
    ){
        items(sectionCount){
            SectionItem(
                modifier = Modifier.fillMaxWidth(),
                onSectionTitleValueChange = {},
                onSectionContentValueChange = {}
            )
        }
    }

}


@Composable
fun SectionItem(
    modifier: Modifier = Modifier,
    onSectionTitleValueChange: (String) -> Unit,
    onSectionContentValueChange: (String) -> Unit

) {


    AddScreenTextField(
        modifier = modifier,
        value = "",
        onValueChange = onSectionTitleValueChange,
        placeholder = stringResource(id = R.string.section_title)
    )
    SpacerHeight(Paddings.smallPadding)
    AddScreenTextField(
        modifier = modifier,
        value = "",
        onValueChange = onSectionContentValueChange,
        placeholder = stringResource(id = R.string.section_content)
    )
    SpacerHeight(Paddings.smallPadding)
    CircleImage(widthDivisionTo = 6)
    SpacerHeight(Paddings.smallPadding)


//type
}


@Composable
private fun SectionsTitle(text: String) {

    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge
    )
    
}


//sayfanın genişliğine göre parametre alsın
@Composable
private fun CircleImage(
    screenWidth : Int = LocalConfiguration.current.screenWidthDp,
    widthDivisionTo : Int
){
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = stringResource(id = R.string.image),
        modifier = Modifier
            .size((screenWidth / widthDivisionTo).dp)
            .clip(CircleShape)
            .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
            
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddScreenTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder : String = "",
    onValueChange: (String)->Unit,
) {
    OutlinedTextField(
        modifier = modifier
            .border(2.dp, MaterialTheme.colorScheme.primary),
        value = value,
        placeholder = {
            Text(text = placeholder)
        },
        onValueChange = onValueChange
    )
}




@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CustomDialog(
    modifier: Modifier = Modifier,
    sectionTitle: String,
    sectionContent: String,
    onSectionTitleValueChange: (String) -> Unit,
    onSectionContentValueChange: (String) -> Unit,
    onConfirmNewSection: (String,String) -> Unit,
    onCancelSection: () -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    AlertDialog(
        onDismissRequest = onCancelSection,
        text = {
            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                SectionsTitle(text = stringResource(id = R.string.add_new_section))
                SpacerHeight(Paddings.smallPadding)
                OutlinedTextField(
                    value =sectionTitle ,
                    onValueChange = onSectionTitleValueChange,
                    label = { Text(text = "Title") },
                    singleLine = true,
                    maxLines = 2,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = androidx.compose.ui.text.input.ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            keyboardController?.show()
                        }
                    )
                )
                SpacerHeight(Paddings.mediumPadding)

                BasicTextField(
                    value = sectionContent,
                    onValueChange = onSectionContentValueChange,
                    textStyle = TextStyle(color = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp, max = 200.dp),

                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmNewSection(sectionTitle,sectionContent)
                }
            ) {
                Text(text = stringResource(id = R.string.ok))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onCancelSection()
                }
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    )
}

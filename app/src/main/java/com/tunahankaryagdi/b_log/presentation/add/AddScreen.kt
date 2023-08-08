package com.tunahankaryagdi.b_log.presentation.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.presentation.components.SpacerHeight
import com.tunahankaryagdi.b_log.utils.Paddings


enum class Type{
    Text,SubtitleAndContent,Image,Code,Link
}


@Composable
fun AddScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: AddViewModel = hiltViewModel()
) {

    val uiState : AddUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sectionUiState : SectionUiState by viewModel.sectionUiState.collectAsStateWithLifecycle()


    AddScreen(
        modifier = modifier,
        uiState = uiState,
        sectionUiState = sectionUiState,
        onTitleValueChange = viewModel::onTitleChange,
        onCancelSection =viewModel::onCancelSection ,
        onConfirm = viewModel::onConfirmNewSection,
        onClickAddButtons = viewModel::onClickAddButtons,
        onSubtitleValueChange =viewModel::onSubtitleValueChange,
        onContentValueChange =  viewModel::onContentValueChange
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(

    modifier: Modifier = Modifier,
    uiState: AddUiState,
    sectionUiState: SectionUiState,
    onTitleValueChange: (String)->Unit,
    onCancelSection: () -> Unit,
    onConfirm: () -> Unit,
    onClickAddButtons: (Type) -> Unit,
    onSubtitleValueChange: (String) -> Unit,
    onContentValueChange: (String) -> Unit


){


    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .padding(horizontal = Paddings.smallPadding),
                title = {

                },
                navigationIcon = {
                    Image(imageVector = Icons.Default.Close, contentDescription = "close the add screen")
                },
                actions = {
                    TextButton(
                        onClick = { },
                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 5.dp)
                    ) {
                        Text("Save")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
            )
        }
    ) {
        AddScreenContent(
            modifier = modifier
                .padding(it),
            uiState = uiState,
            sectionUiState = sectionUiState,
            onCancelSection = onCancelSection ,
            onConfirm = onConfirm,
            onClickAddButtons = onClickAddButtons,
            onSubtitleValueChange =onSubtitleValueChange,
            onContentValueChange =  onContentValueChange,
            onTitleValueChange = onTitleValueChange
        )
    }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreenContent(
    modifier: Modifier = Modifier,
    uiState: AddUiState,
    sectionUiState: SectionUiState,
    onTitleValueChange: (String)->Unit,
    onCancelSection: () -> Unit,
    onClickAddButtons :(Type)->Unit,
    onSubtitleValueChange : (String) -> Unit,
    onContentValueChange : (String) -> Unit,
    onConfirm : () -> Unit
){



    if (uiState.showDialog){
        SectionDialog(
            sectionTitle = sectionUiState.sectionTitle,
            sectionContent = sectionUiState.sectionContent,
            selectedType = uiState.selectedType,
            onCancelSection = onCancelSection,
            onConfirm = onConfirm,
            onSubtitleValueChange = onSubtitleValueChange,
            onContentValueChange = onContentValueChange
        )
    }

    //subtitle title ,text,image,code,link



    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Paddings.smallPadding)
    ){

        LazyColumn() {

            item {
                TextField(
                    value = uiState.title,
                    onValueChange = onTitleValueChange,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        errorBorderColor = Color.Transparent,
                    )
                )
            }

            items(uiState.sections.size){index->

                when(uiState.sections[index].type){

                    Type.Text ->{
                        Text(
                            text = uiState.sections[index].sectionContent,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Type.SubtitleAndContent ->{
                            Text(
                                text = uiState.sections[index].sectionTitle,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = uiState.sections[index].sectionContent,
                                style = MaterialTheme.typography.bodyMedium

                            )

                    }
                    Type.Code ->{
                        
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.onSecondary, RectangleShape)
                                .padding(vertical = Paddings.smallPadding)
                        ) {
                            Text(
                                text = uiState.sections[index].sectionContent,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }

                    }
                    Type.Link ->{
                        val url = uiState.sections[index].sectionContent
                        val text  = buildAnnotatedString {
                            append("URL: ")
                            withStyle(style = SpanStyle(color = Color.Cyan), ) {
                                pushStringAnnotation(tag = "URL", annotation = url )
                                append(url)
                            }
                        }
                        ClickableText(text = text , onClick = {})
                    }
                    Type.Image ->{

                    }

                }
                SpacerHeight(Paddings.smallPadding)
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {

            IconButton(onClick = { onClickAddButtons(Type.Text) }) {
                Image(imageVector = Icons.Default.Clear, contentDescription = "Text")
            }
            IconButton(onClick = { onClickAddButtons(Type.SubtitleAndContent)}) {
                Image(imageVector = Icons.Default.Edit, contentDescription = "Title content")
            }
            IconButton(onClick = { onClickAddButtons(Type.Code) }) {
                Image(imageVector = Icons.Default.AddCircle, contentDescription = "Code")
            }
            IconButton(onClick = { onClickAddButtons(Type.Link)}) {
                Image(imageVector = Icons.Default.Lock, contentDescription = "Link")
            }
            IconButton(onClick = { onClickAddButtons(Type.Image)}) {
                Image(imageVector = Icons.Default.Add, contentDescription = "Image")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SectionDialog(
    modifier: Modifier = Modifier,
    sectionTitle :String,
    sectionContent: String,
    selectedType: Type,
    onCancelSection: () -> Unit,
    onSubtitleValueChange : (String) ->Unit,
    onContentValueChange: (String) -> Unit,
    onConfirm : () -> Unit
) {


    AlertDialog(
        onDismissRequest = onCancelSection,
        text = {
            Column(
                modifier = modifier.fillMaxWidth()
            ) {


            when(selectedType){

                Type.Text ->{
                    SectionTitle(text = "Add text")
                    SpacerHeight(Paddings.smallPadding)
                    TextField(
                        value = sectionContent,
                        onValueChange = onContentValueChange,
                        textStyle = MaterialTheme.typography.bodyLarge,
                        placeholder = {
                            Text(text = "Text")
                        }
                    )
                }
                Type.SubtitleAndContent ->{
                    SectionTitle(text = "Add subtitle and text")
                    TextField(
                        value = sectionTitle,
                        onValueChange = onSubtitleValueChange,
                        placeholder = {
                            Text(text = "Subtitle")
                        },
                        singleLine = true
                    )
                    SpacerHeight(Paddings.smallPadding)
                    TextField(
                        value = sectionContent,
                        onValueChange = onContentValueChange,
                        placeholder = {
                            Text(text = "Text")
                        }
                    )

                }
                Type.Code ->{
                    SectionTitle(text = "Add code part")
                    SpacerHeight(Paddings.smallPadding)
                    TextField(
                        value = sectionContent,
                        onValueChange = onContentValueChange,
                        textStyle = MaterialTheme.typography.bodyLarge,
                        maxLines = Int.MAX_VALUE,
                        placeholder = {
                            Text(text = "Code")
                        }
                    )

                }
                Type.Link ->{
                    SectionTitle(text = "Add a link")
                    SpacerHeight(Paddings.smallPadding)
                    TextField(
                        value = sectionContent,
                        onValueChange = onContentValueChange,
                        textStyle = MaterialTheme.typography.bodyLarge,
                        singleLine = true,
                        placeholder = {
                            Text(text = "http://")
                        }
                    )

                }
                Type.Image ->{
                    ElevatedButton(onClick = { }) {
                        Text(text = "From gallery")
                    }
                    SpacerHeight(Paddings.smallPadding)

                    ElevatedButton(onClick = { }) {
                        Text(text = "From camera")
                    }
                }

            }




            }
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm

            ) {
                Text(text = stringResource(id = R.string.ok))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onCancelSection
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    )
}


@Composable
private fun SectionTitle(
    modifier: Modifier = Modifier,
    text : String
) {
    Text(
        modifier  = modifier,
        text = text,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
    )
}







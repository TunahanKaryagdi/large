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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
                title = {

                },
                navigationIcon = {
                    Image(imageVector = Icons.Default.Close, contentDescription = "close the add screen")
                },
                actions = {
                    TextButton(onClick = { }) {
                        Text("Save")
                    }
                }
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
            onContentValueChange =  onContentValueChange
        )
    }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreenContent(
    modifier: Modifier = Modifier,
    uiState: AddUiState,
    sectionUiState: SectionUiState,
    onCancelSection: () -> Unit,
    onClickAddButtons :(Type)->Unit,
    onSubtitleValueChange : (String) -> Unit,
    onContentValueChange : (String) -> Unit,
    onConfirm : () -> Unit
){

    var text by remember {
        mutableStateOf("text")
    }

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

                    value = text,
                    onValueChange = {
                      text = it
                    },
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
                        Column() {
                            Text(
                                text = uiState.sections[index].sectionTitle,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = uiState.sections[index].sectionContent,
                                style = MaterialTheme.typography.bodyMedium

                            )
                        }
                    }
                    Type.Code ->{
                        
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.onSecondary, RectangleShape)
                                .padding(Paddings.smallPadding)
                        ) {
                            Text(
                                text = uiState.sections[index].sectionContent,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                    }
                    Type.Link ->{

                    }
                    Type.Image ->{

                    }

                }

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
                    OutlinedTextField(value = sectionContent, onValueChange = onContentValueChange )
                }
                Type.SubtitleAndContent ->{
                    Column() {
                        OutlinedTextField(
                            value = sectionTitle,
                            onValueChange = onSubtitleValueChange,
                            placeholder = {
                                Text(text = stringResource(id = R.string.subtitle))
                            }
                        )
                        SpacerHeight(Paddings.smallPadding)
                        OutlinedTextField(
                            modifier = Modifier
                                .wrapContentHeight(),

                            value = sectionContent,
                            onValueChange = onContentValueChange,
                            placeholder = {
                                          Text(text = stringResource(id = R.string.content))
                            },
                            maxLines = 5,
                            singleLine = false
                        )

                    }
                }
                Type.Link ->{
                    OutlinedTextField(value = sectionContent, onValueChange =onContentValueChange )

                }
                Type.Code ->{
                    OutlinedTextField(value = sectionContent, onValueChange =onContentValueChange )

                }
                Type.Image ->{
                    OutlinedTextField(value = sectionContent, onValueChange = onContentValueChange )

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







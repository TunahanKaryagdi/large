package com.tunahankaryagdi.b_log.presentation.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.presentation.components.SpacerHeight
import com.tunahankaryagdi.b_log.utils.Paddings


enum class Type{
    Text,Title,Image
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
        sectionContent = sectionUiState.sectionContent,
        onTitleValueChange = viewModel::onTitleChange,
        onSectionContentValueChange = viewModel::onSectionContentChange,
        onCancelSection =viewModel::onCancelSection ,
        onClickTag = viewModel::onClickTag,
        onClickAddSection = viewModel::onClickAddSection,
        onClickSave = viewModel::onClickSave,
        onClickDropdownItem = viewModel::onClickDropdownItem,
        onDismiss = viewModel::onDismiss,
        onExpand = viewModel::onExpand,
        onConfirm = viewModel::onConfirmNewSection
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    modifier: Modifier = Modifier,
    uiState: AddUiState,
    sectionContent: String,
    screenHeight : Int = LocalConfiguration.current.screenHeightDp,
    onSectionContentValueChange: (String) -> Unit,
    onCancelSection: () -> Unit,
    onTitleValueChange: (String) -> Unit,
    onClickTag: (String, Boolean) -> Unit,
    onClickAddSection: () -> Unit,
    onClickSave: ()-> Unit,
    onClickDropdownItem: (Type) -> Unit,
    onDismiss: () -> Unit,
    onExpand: (Boolean) -> Unit,
    onConfirm : (String,Type) -> Unit
){

    if (uiState.showDialog){
        SectionDialog(
            sectionContent = sectionContent,
            onSectionContentValueChange = onSectionContentValueChange,
            expanded = uiState.isExpanded,
            selectedType = uiState.selectedType ,
            onCancelSection = onCancelSection,
            onClickDropdownItem =  onClickDropdownItem,
            onDismiss = onDismiss,
            onExpand = onExpand,
            onConfirm = onConfirm

        )
    }

    LazyColumn(
        modifier = modifier
            .padding(Paddings.smallPadding)
    ){

        item {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = uiState.title,
                onValueChange = onTitleValueChange,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                ),
                textStyle = MaterialTheme.typography.titleMedium,
                placeholder = {
                    Text(text = stringResource(id = R.string.title))
                },
                maxLines = 2
            )
            SpacerHeight(Paddings.smallPadding)
        }

        item {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight / 5f).dp),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "newblog"
            )
            SpacerHeight(Paddings.smallPadding)
        }

        item {


            IconButton(onClick = onClickAddSection) {
                Icon(
                    imageVector = Icons.Rounded.AddCircle,
                    contentDescription = "add_icon"
                )
            }

        }
        items(uiState.sections.size){index->

            when(uiState.sections[index].type){

                Type.Text ->{

                    SectionItem {
                        Text(
                            text = uiState.sections[index].sectionContent,
                            modifier = it
                        )
                    }

                }

                Type.Title ->{

                    SectionItem {
                        Text(
                            text = uiState.sections[index].sectionContent,
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            modifier = it
                        )
                    }

                }

                Type.Image ->{

                }


            }

        }

    }
}


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SectionDialog(
    modifier: Modifier = Modifier,
    sectionContent: String,
    expanded: Boolean,
    selectedType: Type,
    onSectionContentValueChange: (String) -> Unit,
    onCancelSection: () -> Unit,
    onClickDropdownItem: (Type)->Unit,
    onDismiss: ()->Unit,
    onExpand: (Boolean)->Unit,
    onConfirm : (String,Type) -> Unit
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

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = onExpand
                ) {
                    OutlinedTextField(
                        value = selectedType.name,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown button")
                        },
                        textStyle = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest =  onDismiss
                    ) {

                        Type.values().map {
                            DropdownMenuItem(
                                text = { Text(text = it.name) },
                                onClick = {
                                    onClickDropdownItem(it)
                                    onDismiss()
                                }
                            )
                        }
                    }
                }

                SpacerHeight(Paddings.smallPadding)

                if (selectedType == Type.Image){

                    //TODO:
                    Text(text = "Pick Image")
                }

                else{
                    OutlinedTextField(
                        value = sectionContent ,
                        onValueChange = onSectionContentValueChange,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        label = { Text(text = "Content") },
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
                }

            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(sectionContent,selectedType)
                }
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
private fun SectionsTitle(text: String) {

    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge
    )

}

@Composable
private fun SectionItem(
     modifier: Modifier = Modifier,
     item : @Composable (Modifier) ->Unit
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        item(
            modifier.weight(1f)
        )

        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Clear, contentDescription = "remove")
        }
    }

}





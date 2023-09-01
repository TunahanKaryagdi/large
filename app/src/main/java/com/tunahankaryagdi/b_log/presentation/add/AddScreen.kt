package com.tunahankaryagdi.b_log.presentation.add

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Icon
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.presentation.components.SpacerHeight
import com.tunahankaryagdi.b_log.utils.Paddings


enum class Type{
    Text,SubtitleAndContent,Image,Code,Link
}
//subtitle title ,text,image,code,link


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
        onUriChange = viewModel::onUriChange,
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
    onUriChange: (Uri?) ->Unit,
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
                    Icon(imageVector = Icons.Default.Close, contentDescription = stringResource(id = R.string.close_screen))
                },
                actions = {
                    TextButton(
                        onClick = { },
                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 5.dp)
                    ) {
                        Text(stringResource(id = R.string.save))
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
            onTitleValueChange = onTitleValueChange,
            onUriChange =  onUriChange,
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
    onTitleValueChange: (String)->Unit,
    onUriChange: (Uri?) ->Unit,
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

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult ={uri ->
            onUriChange(uri)
        }
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Paddings.smallPadding)
    ){
        LazyColumn() {

            item {
                    TitleTextField(value = uiState.title, onTitleValueChange = onTitleValueChange)
            }
            item {
                if (uiState.selectedImage == null){
                        SelectImageSection (
                            onClickAddImage = {
                                photoPickerLauncher.launch(
                                    PickVisualMediaRequest(
                                        ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            }
                        )
                    
                }
                else{

                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.cancel_image),
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable {
                                onUriChange(null)
                            }
                    )
                    AsyncImage(
                        model = uiState.selectedImage,
                        contentDescription = stringResource(id = R.string.blog_image),
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(175.dp),

                    )
                }
                SpacerHeight(Paddings.smallPadding)
            }

            items(uiState.sections.size){index->

                when(uiState.sections[index].type){

                    Type.Text ->{
                        Text(
                            text = uiState.sections[index].sectionContent,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Type.SubtitleAndContent ->{
                            Text(
                                text = uiState.sections[index].sectionTitle,
                                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = uiState.sections[index].sectionContent,
                                style = MaterialTheme.typography.titleMedium

                            )

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
                                style = MaterialTheme.typography.titleMedium,
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
                        ClickableText(
                            text = text ,
                            onClick = {},
                            style = MaterialTheme.typography.titleMedium
                        )
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
                Image(painter = painterResource(id = R.drawable.ic_text), contentDescription = stringResource(
                    id = R.string.add_text
                ))
            }
            IconButton(onClick = { onClickAddButtons(Type.SubtitleAndContent)}) {
                Image(painter = painterResource(id = R.drawable.ic_subtitle_and_text), contentDescription = stringResource(
                    id = R.string.add_subtitle_and_text
                ))
            }
            IconButton(onClick = { onClickAddButtons(Type.Code) }) {
                Image(painter = painterResource(id = R.drawable.ic_code), contentDescription = stringResource(
                    id = R.string.add_a_code_part
                ))
            }
            IconButton(onClick = { onClickAddButtons(Type.Link)}) {
                Image(painter = painterResource(id = R.drawable.ic_link), contentDescription = stringResource(
                    id = R.string.add_a_link
                ))
            }
            IconButton(onClick = { onClickAddButtons(Type.Image)}) {
                Image(painter = painterResource(id = R.drawable.ic_image), contentDescription = stringResource(
                    id = R.string.add_image
                ))
            }
        }
    }
}


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
                    SectionTitle(text = stringResource(id = R.string.add_text))
                    SpacerHeight(Paddings.smallPadding)
                    TextField(
                        value = sectionContent,
                        onValueChange = onContentValueChange,
                        textStyle = MaterialTheme.typography.bodyLarge,
                        placeholder = {
                            Text(text = stringResource(id = R.string.text))
                        }
                    )
                }
                Type.SubtitleAndContent ->{
                    SectionTitle(text = stringResource(id = R.string.add_subtitle_and_text))
                    TextField(
                        value = sectionTitle,
                        onValueChange = onSubtitleValueChange,
                        placeholder = {
                            Text(text = stringResource(id = R.string.subtitle))
                        },
                        singleLine = true
                    )
                    SpacerHeight(Paddings.smallPadding)
                    TextField(
                        value = sectionContent,
                        onValueChange = onContentValueChange,
                        placeholder = {
                            Text(text = stringResource(id = R.string.text))
                        }
                    )

                }
                Type.Code ->{
                    SectionTitle(text = stringResource(id = R.string.add_a_code_part))
                    SpacerHeight(Paddings.smallPadding)
                    TextField(
                        value = sectionContent,
                        onValueChange = onContentValueChange,
                        textStyle = MaterialTheme.typography.bodyLarge,
                        maxLines = Int.MAX_VALUE,
                        placeholder = {
                            Text(text = stringResource(id = R.string.code))
                        }
                    )

                }
                Type.Link ->{
                    SectionTitle(text = stringResource(id = R.string.add_a_link))
                    SpacerHeight(Paddings.smallPadding)
                    TextField(
                        value = sectionContent,
                        onValueChange = onContentValueChange,
                        textStyle = MaterialTheme.typography.bodyLarge,
                        singleLine = true,
                        placeholder = {
                            Text(text = stringResource(id = R.string.http))
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
private fun TitleTextField(
    value :String,
    onTitleValueChange : (String)->Unit
){
    TextField(
        value = value,
        onValueChange = onTitleValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        textStyle = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold, textAlign = TextAlign.Center),
        colors = TextFieldDefaults.colors(
            cursorColor = Color.Black,
            focusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,

            errorIndicatorColor = Color.Transparent,

            )
    )
}

@Composable
private fun SelectImageSection(
    onClickAddImage: ()->Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(175.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary)
            .clickable {
                onClickAddImage()
            }

    ){
        Icon(imageVector = Icons.Default.AddCircle, contentDescription = stringResource(
            id = R.string.add_image
        ))
    }
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







package com.tunahankaryagdi.b_log.presentation.add

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.b_log.data.model.blog.NewBlogRequest
import com.tunahankaryagdi.b_log.data.model.blog.NewBlogSection
import com.tunahankaryagdi.b_log.di.MyApplication
import com.tunahankaryagdi.b_log.domain.use_case.PostBlogUseCase
import com.tunahankaryagdi.b_log.domain.use_case.PostImageUseCase
import com.tunahankaryagdi.b_log.utils.Resource
import com.tunahankaryagdi.b_log.utils.SectionTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val postImageUseCase: PostImageUseCase,
    private val postBlogUseCase: PostBlogUseCase,
    private val application: MyApplication
) : ViewModel() {

    private val _uiState: MutableStateFlow<AddUiState> = MutableStateFlow(AddUiState())
    val uiState = _uiState

    private val _sectionUiState: MutableStateFlow<SectionUiState> = MutableStateFlow(SectionUiState())
    val sectionUiState = _sectionUiState


    fun onTitleChange(value :String){
        _uiState.value = _uiState.value.copy(title = value)
    }

    fun onUriChange(uri: Uri?){
        _uiState.value = _uiState.value.copy(selectedImage = uri)
    }

    fun onSubtitleValueChange(value :String){
        _sectionUiState.value = _sectionUiState.value.copy(sectionTitle = value)
    }

    fun onContentValueChange(value :String){
        _sectionUiState.value = _sectionUiState.value.copy(sectionContent = value)
    }

    fun onCancelSection(){
        _uiState.value = _uiState.value.copy(showDialog = false)
        _sectionUiState.value = _sectionUiState.value.copy(sectionContent = "" , sectionTitle = "")
    }

    fun onClickAddButtons(type: SectionTypes){
        _uiState.value = _uiState.value.copy(selectedType = type, showDialog = true)
    }


    fun onConfirmNewSection(){

        when(_uiState.value.selectedType){

            SectionTypes.TITLE_TEXT->{
                val newSection = SectionUiState(
                    type = _uiState.value.selectedType,
                    sectionTitle = _sectionUiState.value.sectionTitle,
                    sectionContent = _sectionUiState.value.sectionContent
                )
                _uiState.value = _uiState.value.copy(sections = _uiState.value.sections.addAndReturn(newSection), showDialog = false)
            }
            else ->{
                val newSection = SectionUiState(
                    type = _uiState.value.selectedType,
                    sectionTitle = _sectionUiState.value.sectionTitle,
                    sectionContent = _sectionUiState.value.sectionContent
                )
                _uiState.value = _uiState.value.copy(sections = _uiState.value.sections.addAndReturn(newSection), showDialog = false)
            }
        }
        _sectionUiState.value = _sectionUiState.value.copy(sectionContent = "" , sectionTitle = "")
    }

    fun onClickPost(context: Context){


            viewModelScope.launch {
                postImage(context)

                delay(2000)
                val sections = _uiState.value.sections.map {section->
                    NewBlogSection(section.sectionTitle,section.sectionContent,section.sectionImage,section.type.name)
                }
                val newBlog = NewBlogRequest(
                    title = _uiState.value.title,
                    image = _uiState.value.imageUrl,
                    published = true,
                    authorId = application.getUserId(),
                    tags = _uiState.value.tags,
                    sections = sections
                )

                _uiState.value = _uiState.value.copy(isLoading = true)
                postBlogUseCase.invoke(newBlog).collect{resource->

                    when(resource){

                        is Resource.Success->{
                            _uiState.value = AddUiState()

                        }
                        is Resource.Error->{
                            _uiState.value = _uiState.value.copy(error = resource.message, isLoading = false)
                        }
                    }

                }

            }





    }




    private suspend fun postImage(context: Context){
        uiState.value.selectedImage?.let {uri->

            val file = uri.toFile(context) ?: return

            _uiState.value = _uiState.value.copy(isLoading = true)
            postImageUseCase.invoke(file).collect{ resource->

                when(resource){
                    is Resource.Success->{
                        _uiState.value = _uiState.value.copy(imageUrl = resource.data.url, isPhotoLoading = false)
                    }
                    is Resource.Error->{
                        _uiState.value = _uiState.value.copy(error = resource.message, isPhotoLoading = false)
                    }
                }
            }
        }
    }

    private fun Uri.toFile(context: Context) : File?{

        val inputStream = context.contentResolver.openInputStream(this)
        val tempFile = File.createTempFile("temp", ".png")
        return try {
            tempFile.outputStream().use { fileOut ->
                inputStream?.copyTo(fileOut)
            }
            tempFile.deleteOnExit()
            inputStream?.close()
            tempFile
        } catch (e: Exception) {
            null
        }
    }

    private fun Uri.fileConverter(context: Context) : File?{
        val contentResolver = context.contentResolver
        try {
            val inputStream = contentResolver.openInputStream(this)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val file = File(context.cacheDir, "image.png")
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            file
            return file
        }
        catch (e: Exception){
            return null
        }

    }




    private fun <T> MutableList<T>.addAndReturn(item : T) : MutableList<T>{
        this.add(item)
        return this
    }


}


data class AddUiState(
    val isLoading: Boolean = false,
    val isPhotoLoading: Boolean = false,
    val error: String = "",
    val title: String = "",
    val selectedImage: Uri? = null,
    val imageUrl: String = "",
    val tags: MutableList<String> = mutableListOf(),
    val sections: MutableList<SectionUiState> = mutableListOf(),
    val isPublished: Boolean = false,
    val showDialog : Boolean = false,
    val selectedType: SectionTypes = SectionTypes.TEXT
)

data class SectionUiState(
    val sectionTitle: String = "",
    val sectionContent: String = "",
    val sectionImage: String = "",
    val type :SectionTypes = SectionTypes.TEXT
)
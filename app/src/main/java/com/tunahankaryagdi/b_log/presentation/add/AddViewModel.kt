package com.tunahankaryagdi.b_log.presentation.add

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.tunahankaryagdi.b_log.utils.SectionTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor() : ViewModel() {

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


    fun <T> MutableList<T>.addAndReturn(item : T) : MutableList<T>{
        this.add(item)
        return this
    }


}


data class AddUiState(
    val title: String = "",
    val selectedImage: Uri? = null,
    val tags: MutableList<String> = mutableListOf(),
    val sections: MutableList<SectionUiState> = mutableListOf(),
    val isPublished: Boolean = false,
    val showDialog : Boolean = false,
    val selectedType: SectionTypes = SectionTypes.TEXT
)

data class SectionUiState(
    val sectionTitle: String = "",
    val sectionContent: String = "",
    val type :SectionTypes = SectionTypes.TEXT
)
package com.tunahankaryagdi.b_log.presentation.add

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor() : ViewModel() {

    private val _uiState: MutableStateFlow<AddUiState> = MutableStateFlow(AddUiState())
    val uiState = _uiState

    private val _sectionUiState: MutableStateFlow<SectionUiState> = MutableStateFlow(SectionUiState())
    val sectionUiState = _sectionUiState


    fun onTitleChange(title: String){
        _uiState.value = _uiState.value.copy(title = title)
    }

    fun onSectionTitleChange(sectionTitle: String){
        _sectionUiState.value = _sectionUiState.value.copy(sectionTitle = sectionTitle)
    }

    fun onSectionContentChange(sectionContent: String){
        _sectionUiState.value = _sectionUiState.value.copy(sectionContent = sectionContent)
    }

    fun onConfirmNewSection(title: String, content: String){

        val newSection = SectionUiState(sectionTitle = title, sectionContent = content)
        _uiState.value = _uiState.value.copy(sections = _uiState.value.sections.addAndReturn(newSection), showDialog = false)

    }

    fun onCancelSection(){
        _uiState.value = _uiState.value.copy(showDialog = false)
    }

    fun onClickAddSection(){
        _uiState.value = _uiState.value.copy(showDialog = true)
    }

    fun onClickTag(tagName: String, isSelected: Boolean){
        val tagList = _uiState.value.tags
        if (isSelected){
            _uiState.value = _uiState.value.copy(tags = tagList.addAndReturn(tagName))
            return
        }
        tagList.remove(tagName)
        _uiState.value = _uiState.value.copy(tags = tagList)

    }

    fun onClickSave(){
        println(_uiState.value.sections)
        println(_uiState.value.tags)

    }

    fun <T> MutableList<T>.addAndReturn(item : T) : MutableList<T>{
        this.add(item)
        return this
    }

}


data class AddUiState(
    val title: String = "",
    val image: String = "",
    val tags: MutableList<String> = mutableListOf(),
    val sections: MutableList<SectionUiState> = mutableListOf(),
    val isPublished: Boolean = false,
    val showDialog : Boolean = false

)

data class SectionUiState(
    val sectionTitle: String = "",
    val sectionContent: String = "",
    val image: String = "" ,
    val type :String = ""
)
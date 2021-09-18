package com.avinash.test.githubtrendingrepos.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avinash.test.githubtrendingrepos.presentation.state.BaseViewState
import kotlinx.coroutines.launch

abstract class BaseViewModel<VS: BaseViewState>: ViewModel() {
    private val mutableLiveData: MutableLiveData<VS> = MutableLiveData()
    val viewState: LiveData<VS>
        get() = mutableLiveData

    fun updateLiveData(state: VS) {
        viewModelScope.launch {
            mutableLiveData.value = state
        }
    }
}
package com.avinash.test.githubtrendingrepos.presentation.viewmodel

import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.avinash.test.githubtrendingrepos.database.model.RepositoryItem
import com.avinash.test.githubtrendingrepos.domain.RepositoryDetailUseCase
import com.avinash.test.githubtrendingrepos.domain.UseCase
import com.avinash.test.githubtrendingrepos.presentation.state.RepositoryDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailViewModel @Inject constructor(
        private val useCase: UseCase<RepositoryItem>
) : BaseViewModel<RepositoryDetailState>() {

    private val observer = Observer<RepositoryItem> {
        updateLiveData(RepositoryDetailState.ShowDetailsState(item = it))
    }

    fun loadDetails(repositoryId: Int?) {
        useCase.liveData.observeForever(observer)
        viewModelScope.launch {
            if (repositoryId != null) {
                executeUseCase(repositoryId = repositoryId)
            }
        }
    }

    private suspend fun executeUseCase(repositoryId: Int) {
        useCase.setData(RepositoryDetailUseCase.ID_KEY, repositoryId)
        useCase.execute()
    }

    override fun onCleared() {
        useCase.liveData.removeObserver(observer)
        viewModelScope.coroutineContext.cancelChildren()
        super.onCleared()
    }
}
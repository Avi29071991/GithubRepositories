package com.avinash.test.githubtrendingrepos.presentation.viewmodel

import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.avinash.test.githubtrendingrepos.database.model.ItemHolder
import com.avinash.test.githubtrendingrepos.domain.RepositoriesUseCase
import com.avinash.test.githubtrendingrepos.domain.UseCase
import com.avinash.test.githubtrendingrepos.presentation.state.RepositoryListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryListViewModel @Inject constructor(
    private val useCase: UseCase<ItemHolder>
) : BaseViewModel<RepositoryListViewState>() {

    private val observer = Observer<ItemHolder> {
        updateLiveData(RepositoryListViewState.ShowInitialListItems(items = it.items))
    }

    fun loadTrendingRepositories() {
        useCase.liveData.observeForever(observer)
        updateLiveData(RepositoryListViewState.LoadingListState)
        viewModelScope.launch {
            executeUseCase()
        }
    }

    private suspend fun executeUseCase() {
        useCase.setData(key = RepositoriesUseCase.RESULT_PER_PAGE, MAX_RESULTS)
        useCase.setData(key = RepositoriesUseCase.PAGE_NUMBER, PAGE_NUMBER)
        useCase.execute()
    }

    override fun onCleared() {
        useCase.liveData.removeObserver(observer)
        viewModelScope.coroutineContext.cancelChildren()
        super.onCleared()
    }

    companion object {
        private const val MAX_RESULTS = 100
        private const val PAGE_NUMBER = 1
    }
}
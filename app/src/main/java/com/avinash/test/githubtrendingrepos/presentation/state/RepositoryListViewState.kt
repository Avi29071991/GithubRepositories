package com.avinash.test.githubtrendingrepos.presentation.state

import com.avinash.test.githubtrendingrepos.database.model.RepositoryItem

sealed class RepositoryListViewState: BaseViewState {
    object LoadingListState: RepositoryListViewState()
    data class ShowInitialListItems(val items: List<RepositoryItem>): RepositoryListViewState()
}

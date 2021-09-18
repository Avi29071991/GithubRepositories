package com.avinash.test.githubtrendingrepos.presentation.state

import com.avinash.test.githubtrendingrepos.database.model.RepositoryItem

sealed class RepositoryDetailState: BaseViewState {
    data class ShowDetailsState(val item: RepositoryItem): RepositoryDetailState()
}

package com.avinash.test.githubtrendingrepos.api.repository

import com.avinash.test.githubtrendingrepos.api.model.RepositoryResponse
import retrofit2.Callback

interface RemoteRepository {
    suspend fun getTrendingRepositories(
            resultsPerPage: Int,
            pageNumber: Int,
            callback: Callback<RepositoryResponse>
    )
}
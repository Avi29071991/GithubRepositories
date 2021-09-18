package com.avinash.test.githubtrendingrepos.api.datasource

import com.avinash.test.githubtrendingrepos.api.GithubApi
import com.avinash.test.githubtrendingrepos.api.model.RepositoryResponse
import retrofit2.Callback
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: GithubApi) {

    fun getTrendingRepositories(
            resultPerPage: Int,
            pageNumber: Int,
            callback: Callback<RepositoryResponse>
    ) {
        val call = api.getRepositories(
                searchQuery = SEARCH_KEYWORD,
                resultPerPage = resultPerPage,
                pageNumber = pageNumber
        )

        call.enqueue(callback)
    }

    companion object {
        private const val SEARCH_KEYWORD = "trending"
    }
}
package com.avinash.test.githubtrendingrepos.api.repository.implementation

import com.avinash.test.githubtrendingrepos.api.datasource.RemoteDataSource
import com.avinash.test.githubtrendingrepos.api.repository.RemoteRepository
import com.avinash.test.githubtrendingrepos.api.model.RepositoryResponse
import retrofit2.Callback
import javax.inject.Inject

class RemoteRepositoryImplementation @Inject constructor(
        private val dataSource: RemoteDataSource
) : RemoteRepository {
    override suspend fun getTrendingRepositories(
            resultsPerPage: Int,
            pageNumber: Int,
            callback: Callback<RepositoryResponse>
    ) {
        return dataSource.getTrendingRepositories(
                resultPerPage = resultsPerPage,
                pageNumber,
                callback
        )
    }
}
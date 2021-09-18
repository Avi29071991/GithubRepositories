package com.avinash.test.githubtrendingrepos.api

import com.avinash.test.githubtrendingrepos.api.model.RepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    fun getRepositories(
        @Query("q") searchQuery: String,
        @Query("per_page") resultPerPage: Int,
        @Query("page") pageNumber: Int
    ): Call<RepositoryResponse>
}
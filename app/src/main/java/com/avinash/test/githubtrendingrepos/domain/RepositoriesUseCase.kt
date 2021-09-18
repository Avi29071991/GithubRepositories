package com.avinash.test.githubtrendingrepos.domain

import android.util.Log
import com.avinash.test.githubtrendingrepos.api.model.RepositoryResponse
import com.avinash.test.githubtrendingrepos.api.repository.RemoteRepository
import com.avinash.test.githubtrendingrepos.database.model.ItemHolder
import com.avinash.test.githubtrendingrepos.database.model.RepositoryItem
import com.avinash.test.githubtrendingrepos.database.repository.DatabaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RepositoriesUseCase @Inject constructor(
        private val remoteRepository: RemoteRepository,
        private val databaseRepository: DatabaseRepository
) : UseCase<ItemHolder>() {

    override suspend fun execute() {
        val pageNumber = fetchData(key = PAGE_NUMBER) as? Int
        val resultsPerPage = fetchData(key = RESULT_PER_PAGE) as? Int
        if (pageNumber != null && resultsPerPage != null) {
            remoteRepository.getTrendingRepositories(
                resultsPerPage,
                pageNumber,
                object : Callback<RepositoryResponse> {
                    override fun onResponse(
                        call: Call<RepositoryResponse>,
                        response: Response<RepositoryResponse>
                    ) {
                        if (response.isSuccessful) {
                            val repoResponse = response.body()
                            repoResponse?.items?.run {
                                val itemsList = this.map { item ->
                                    RepositoryItem(
                                        repositoryId = item.id,
                                        name = item.name,
                                        fullName = item.fullName,
                                        private = item.private,
                                        description = item.description,
                                        url = item.url,
                                        createdAt = item.createdAt,
                                        updatedAt = item.updatedAt,
                                        gitUrl = item.gitUrl,
                                        sshUrl = item.sshUrl,
                                        cloneUrl = item.cloneUrl,
                                        homepage = item.homepage,
                                        size = item.size,
                                        language = item.language,
                                        archived = item.archived,
                                        disabled = item.disabled,
                                        score = item.score
                                    )
                                }

                                CoroutineScope(Dispatchers.Default).launch {
                                    databaseRepository.deleteAllRepositories()
                                    databaseRepository.insertRepository(itemsList)
                                    val value = databaseRepository.getTrendingRepositories()
                                    withContext(Dispatchers.Main) {
                                        liveData.postValue(ItemHolder(items = value))
                                    }
                                }
                            }
                        } else {
                            postValueFromDB()
                        }
                    }

                    override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
                        Log.e(TAG, "Remote repository fetch failed", t)
                        postValueFromDB()
                    }
                })
        }
    }

    private fun postValueFromDB() {
        CoroutineScope(Dispatchers.IO).launch {
            val value = databaseRepository.getTrendingRepositories()
            withContext(Dispatchers.Main) {
                liveData.postValue(ItemHolder(items = value))
            }
        }
    }

    companion object {
        private const val TAG = "RepositoryUseCase"
        const val RESULT_PER_PAGE = "resultsPerPageKey"
        const val PAGE_NUMBER = "pageNumberKey"
    }
}
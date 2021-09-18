package com.avinash.test.githubtrendingrepos.domain

import com.avinash.test.githubtrendingrepos.database.model.RepositoryItem
import com.avinash.test.githubtrendingrepos.database.repository.DatabaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoryDetailUseCase @Inject constructor(
        private val databaseRepository: DatabaseRepository
) : UseCase<RepositoryItem>() {
    override suspend fun execute() {
        val repositoryId = fetchData(ID_KEY) as? Int
        if (repositoryId != null) {
            CoroutineScope(Dispatchers.IO).launch {
                val item = databaseRepository.repository(id = repositoryId)
                liveData.postValue(item)
            }
        }
    }

    companion object {
        const val ID_KEY = "idKey"
    }
}
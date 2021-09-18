package com.avinash.test.githubtrendingrepos.database.datasource

import com.avinash.test.githubtrendingrepos.database.RepositoryDao
import com.avinash.test.githubtrendingrepos.database.model.RepositoryItem
import javax.inject.Inject

class DbDataSource @Inject constructor(private val dao: RepositoryDao) {
    fun getTrendingReposFromDB(): List<RepositoryItem> {
        return dao.allRepositories()
    }

    fun insertRepository(repositories: List<RepositoryItem>) {
        dao.insertRepository(repositories = repositories)
    }

    fun repository(id: Int): RepositoryItem {
        return dao.repository(id = id)
    }

    fun deleteAllRepositories() {
        dao.deleteRepositories()
    }
}
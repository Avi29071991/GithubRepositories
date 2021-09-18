package com.avinash.test.githubtrendingrepos.database.repository.implementation

import com.avinash.test.githubtrendingrepos.database.datasource.DbDataSource
import com.avinash.test.githubtrendingrepos.database.model.RepositoryItem
import com.avinash.test.githubtrendingrepos.database.repository.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImplementation @Inject constructor(
    private val dataSource: DbDataSource
): DatabaseRepository {
    override fun getTrendingRepositories(): List<RepositoryItem> {
        return dataSource.getTrendingReposFromDB()
    }

    override fun insertRepository(repositories: List<RepositoryItem>) {
        dataSource.insertRepository(repositories = repositories)
    }

    override fun repository(id: Int): RepositoryItem {
        return dataSource.repository(id = id)
    }

    override fun deleteAllRepositories() {
        dataSource.deleteAllRepositories()
    }
}
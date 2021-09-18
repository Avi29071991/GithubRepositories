package com.avinash.test.githubtrendingrepos.database.repository

import com.avinash.test.githubtrendingrepos.database.model.RepositoryItem

interface DatabaseRepository {
    fun getTrendingRepositories(): List<RepositoryItem>
    fun insertRepository(repositories: List<RepositoryItem>)
    fun repository(id: Int): RepositoryItem
    fun deleteAllRepositories()
}
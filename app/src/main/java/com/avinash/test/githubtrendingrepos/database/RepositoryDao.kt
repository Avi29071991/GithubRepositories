package com.avinash.test.githubtrendingrepos.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.avinash.test.githubtrendingrepos.database.model.RepositoryItem

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepository(repositories: List<RepositoryItem>)

    @Query("SELECT * FROM repository ORDER BY name COLLATE NOCASE ASC")
    fun allRepositories(): List<RepositoryItem>

    @Query("SELECT * FROM repository WHERE repositoryId = :id")
    fun repository(id: Int): RepositoryItem

    /**
     * Deletes all the records from our database
     */
    @Query("DELETE from repository")
    fun deleteRepositories()
}
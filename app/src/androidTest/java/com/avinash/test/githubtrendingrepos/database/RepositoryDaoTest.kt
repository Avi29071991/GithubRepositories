package com.avinash.test.githubtrendingrepos.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.avinash.test.githubtrendingrepos.database.model.RepositoryItem
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepositoryDaoTest: AbstractDatabaseTest() {

    private lateinit var repositoryDao: RepositoryDao
    private val item1 = getRepositoryItem(123,"TestRepository1", 55)
    private val item2 = getRepositoryItem(155, "TestRepository2", 40)
    private val item3 = getRepositoryItem(201, "TestRepository3", 66)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        repositoryDao = db.repositoryDao()
        repositoryDao.insertRepository(
            listOf(
                item1,
                item2,
                item3
            )
        )
    }

    @Test
    fun testGetRepositoryItems() {
        val list = repositoryDao.allRepositories()
        assertEquals(list.size, 3)

        // Verifying list item : 1
        assertEquals(list[0].name, item1.name)
        assertEquals(list[0].fullName, item1.fullName)
        assertEquals(list[0].gitUrl, item1.gitUrl)

        // Verifying list item : 2
        assertEquals(list[1].name, item2.name)
        assertEquals(list[1].fullName, item2.fullName)
        assertEquals(list[1].gitUrl, item2.gitUrl)

        // Verify list item : 3
        assertEquals(list[2].name, item3.name)
        assertEquals(list[2].fullName, item3.fullName)
        assertEquals(list[2].gitUrl, item3.gitUrl)
    }

    private fun getRepositoryItem(id: Int, name: String, score: Int): RepositoryItem {
        return RepositoryItem(
            repositoryId = id,
            name = name,
            fullName = "authorName/$name",
            private = true,
            description = "Description for $name",
            url = "localhost://test${name}Url",
            gitUrl = "localhost://test${name}GitUrl",
            sshUrl = "localhost://test${name}SSHUrl",
            cloneUrl = "localhost://test${name}CloneUrl",
            score = score
        )
    }

    @After
    fun tearDown() {
        repositoryDao.deleteRepositories()
    }
}
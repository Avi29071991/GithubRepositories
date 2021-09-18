package com.avinash.test.githubtrendingrepos.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.avinash.test.githubtrendingrepos.api.model.Item
import com.avinash.test.githubtrendingrepos.database.model.RepositoryItem

@Database(entities = [RepositoryItem::class], version = 1)
abstract class RepositoryDatabase: RoomDatabase() {

    abstract fun repositoryDao(): RepositoryDao
}
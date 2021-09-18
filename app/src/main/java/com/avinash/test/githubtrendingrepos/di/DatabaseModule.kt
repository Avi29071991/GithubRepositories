package com.avinash.test.githubtrendingrepos.di

import android.content.Context
import androidx.room.Room
import com.avinash.test.githubtrendingrepos.database.RepositoryDao
import com.avinash.test.githubtrendingrepos.database.RepositoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesRepositoryDatabase(@ApplicationContext context: Context): RepositoryDatabase {
        return Room.databaseBuilder(context.applicationContext,
            RepositoryDatabase::class.java, "RepositoryDatabase")
            .build()
    }

    @Provides
    @Singleton
    fun providesRepositoryDao(database: RepositoryDatabase): RepositoryDao {
        return database.repositoryDao()
    }
}
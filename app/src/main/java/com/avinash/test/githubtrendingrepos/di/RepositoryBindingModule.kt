package com.avinash.test.githubtrendingrepos.di

import com.avinash.test.githubtrendingrepos.api.repository.RemoteRepository
import com.avinash.test.githubtrendingrepos.api.repository.implementation.RemoteRepositoryImplementation
import com.avinash.test.githubtrendingrepos.database.model.ItemHolder
import com.avinash.test.githubtrendingrepos.database.model.RepositoryItem
import com.avinash.test.githubtrendingrepos.database.repository.DatabaseRepository
import com.avinash.test.githubtrendingrepos.database.repository.implementation.DatabaseRepositoryImplementation
import com.avinash.test.githubtrendingrepos.domain.RepositoriesUseCase
import com.avinash.test.githubtrendingrepos.domain.RepositoryDetailUseCase
import com.avinash.test.githubtrendingrepos.domain.UseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindingModule {

    @Binds
    abstract fun bindsRemoteRepository(implementation: RemoteRepositoryImplementation): RemoteRepository

    @Binds
    abstract fun bindsDatabaseRepository(implementation: DatabaseRepositoryImplementation): DatabaseRepository

    companion object {
        @Provides
        @Singleton
        fun providesRepositoryListUseCase(
            remoteRepository: RemoteRepository,
            databaseRepository: DatabaseRepository
        ): UseCase<ItemHolder> {
           return RepositoriesUseCase(
               remoteRepository, databaseRepository
           )
        }

        @Provides
        @Singleton
        fun providesRepositoryDetailsUseCase(
            databaseRepository: DatabaseRepository
        ): UseCase<RepositoryItem> {
            return RepositoryDetailUseCase(databaseRepository)
        }
    }
}
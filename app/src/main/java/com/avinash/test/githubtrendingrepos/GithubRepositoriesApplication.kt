package com.avinash.test.githubtrendingrepos

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class GithubRepositoriesApplication: Application(), Configuration.Provider {

    // Worker factory instance which helps in injecting Worker classes
    // This will also be used for configuring work manager on demand
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}
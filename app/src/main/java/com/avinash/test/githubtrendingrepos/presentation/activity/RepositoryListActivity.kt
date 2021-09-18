package com.avinash.test.githubtrendingrepos.presentation.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.work.*
import com.avinash.test.githubtrendingrepos.database.model.RepositoryItem
import com.avinash.test.githubtrendingrepos.databinding.ActivityRepositoryListBinding
import com.avinash.test.githubtrendingrepos.presentation.adapter.RepositoryPagingAdapter
import com.avinash.test.githubtrendingrepos.presentation.state.RepositoryListViewState
import com.avinash.test.githubtrendingrepos.presentation.viewmodel.RepositoryListViewModel
import com.avinash.test.githubtrendingrepos.workmanager.RepositoryWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class RepositoryListActivity: BaseActivity<
        ActivityRepositoryListBinding,
        RepositoryListViewState,
        RepositoryListViewModel>() {
    private var workManager: WorkManager? = null

    override val viewModel by viewModels<RepositoryListViewModel>()

    override fun setViewBinding(
        layoutInflater: LayoutInflater
    ) = ActivityRepositoryListBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
        viewModel.loadTrendingRepositories()
    }

    private fun observeLiveData() {
        viewModel.viewState.observe(this) {
            when(it) {
                is RepositoryListViewState.LoadingListState -> {
                    Log.d("MainActivity", "Loading state in progress")
                    viewBinding.progressCircular.visibility = View.VISIBLE
                    viewBinding.countryList.visibility = View.GONE
                    viewBinding.errorMessage.visibility = View.GONE
                }

                is RepositoryListViewState.ShowInitialListItems -> {
                    viewBinding.progressCircular.visibility = View.GONE
                    if (it.items.isNotEmpty()) {
                        viewBinding.countryList.visibility = View.VISIBLE
                        viewBinding.errorMessage.visibility = View.GONE
                        initAdapter(it.items)
                        enqueueWorkRequest()
                    } else {
                        viewBinding.countryList.visibility = View.GONE
                        viewBinding.errorMessage.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun initAdapter(items: List<RepositoryItem>) {
        val adapter = RepositoryPagingAdapter(items)
        viewBinding.countryList.adapter = adapter
    }

    private fun enqueueWorkRequest() {
        workManager = WorkManager.getInstance(this)

        // Create Network constraint
        val constraints: Constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()


        // Create periodic work request
        val periodicSyncDataWork =
                PeriodicWorkRequest.Builder(RepositoryWorker::class.java, 30, TimeUnit.MINUTES)
                        .setConstraints(constraints) // setting a backoff on case the work needs to retry
                        .setBackoffCriteria(
                                BackoffPolicy.LINEAR,
                                PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                                TimeUnit.MILLISECONDS
                        )
                        .build()

        // Enqueue periodic work request
        workManager?.enqueueUniquePeriodicWork(
                SYNC_DATA_WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,  //Existing Periodic Work policy
                periodicSyncDataWork //work request
        )
    }

    companion object {
        private const val SYNC_DATA_WORK_NAME = "Repository_Sync_Job"
    }
}
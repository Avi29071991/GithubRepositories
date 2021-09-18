package com.avinash.test.githubtrendingrepos.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.avinash.test.githubtrendingrepos.domain.UseCase
import com.avinash.test.githubtrendingrepos.database.model.ItemHolder
import com.avinash.test.githubtrendingrepos.domain.RepositoriesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltWorker
class RepositoryWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workParams: WorkerParameters,
    private val useCase: UseCase<ItemHolder>
): Worker(context, workParams) {
    override fun doWork(): Result {
        CoroutineScope(Dispatchers.IO).launch {
            executeUseCase()
        }

        return Result.success()
    }

    private suspend fun executeUseCase() {
        useCase.setData(key = RepositoriesUseCase.RESULT_PER_PAGE, MAX_RESULTS)
        useCase.setData(key = RepositoriesUseCase.PAGE_NUMBER, PAGE_NUMBER)
        useCase.execute()
    }

    companion object {
        private const val MAX_RESULTS = 100
        private const val PAGE_NUMBER = 1
    }
}
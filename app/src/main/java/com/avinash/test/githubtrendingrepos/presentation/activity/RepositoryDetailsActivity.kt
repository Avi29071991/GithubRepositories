package com.avinash.test.githubtrendingrepos.presentation.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.avinash.test.githubtrendingrepos.R
import com.avinash.test.githubtrendingrepos.database.model.RepositoryItem
import com.avinash.test.githubtrendingrepos.databinding.ActivityRepositoryDetailsBinding
import com.avinash.test.githubtrendingrepos.presentation.state.RepositoryDetailState
import com.avinash.test.githubtrendingrepos.presentation.viewmodel.RepositoryDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryDetailsActivity: BaseActivity<
        ActivityRepositoryDetailsBinding,
        RepositoryDetailState,
        RepositoryDetailViewModel>() {

    override val viewModel by viewModels<RepositoryDetailViewModel>()

    override fun setViewBinding(
        layoutInflater: LayoutInflater
    ) = ActivityRepositoryDetailsBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.toolbar.title = getString(R.string.repository_detail_title)
        setSupportActionBar(viewBinding.toolbar)
        observeViewModelLiveData()
        viewModel.loadDetails(repositoryId = intent.extras?.getInt(REPOSITORY_ID))
    }

    private fun observeViewModelLiveData() {
        viewModel.viewState.observe(this) {
            when(it) {
                is RepositoryDetailState.ShowDetailsState -> {
                    setupUI(it.item)
                }
            }
        }
    }

    private fun setupUI(item: RepositoryItem) {
        viewBinding.repositoryName.text = item.name  ?: ""
        viewBinding.repositoryFullName.text = item.fullName ?: ""
        viewBinding.repositoryUrl.text = item.url ?: ""
        viewBinding.repositoryCloneUrl.text = item.cloneUrl ?: ""
        viewBinding.repositoryGitUrl.text = item.gitUrl ?: ""
        viewBinding.repositorySshUrl.text = item.sshUrl ?: ""
        viewBinding.repositoryDescription.text = item.description ?: ""
        viewBinding.repositoryScore.text = getString(R.string.score_message, item.score.toString())
        viewBinding.repositoryPrivate.text = getString(R.string.private_message, item.private.toString())
    }

    companion object {
        const val REPOSITORY_ID = "repositoryID"
    }
}
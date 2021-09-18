package com.avinash.test.githubtrendingrepos.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avinash.test.githubtrendingrepos.database.model.RepositoryItem
import com.avinash.test.githubtrendingrepos.databinding.RepositoryRowBinding
import com.avinash.test.githubtrendingrepos.presentation.activity.RepositoryDetailsActivity

class RepositoryPagingAdapter(private val repositories: List<RepositoryItem>):
    RecyclerView.Adapter<RepositoryPagingAdapter.RepositoryViewHolder>() {

    inner class RepositoryViewHolder(val binding: RepositoryRowBinding) :
            RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        with(holder) {
            with(repositories[position]) {
                binding.repositoryName.text = name ?: ""
                binding.repositoryDescription.text = description ?: ""
                binding.repositoryUrl.text = url ?: ""

                binding.root.setOnClickListener {
                    val context = binding.root.context
                    val intent = Intent(context, RepositoryDetailsActivity::class.java)
                    repositoryId?.run {
                        intent.putExtra(RepositoryDetailsActivity.REPOSITORY_ID, this)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = RepositoryRowBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

        return RepositoryViewHolder(binding)
    }

    // return the size of currency list
    override fun getItemCount(): Int {
        return repositories.size
    }
}
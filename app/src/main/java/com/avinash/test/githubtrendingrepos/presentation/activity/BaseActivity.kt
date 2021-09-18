package com.avinash.test.githubtrendingrepos.presentation.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.avinash.test.githubtrendingrepos.presentation.state.BaseViewState
import com.avinash.test.githubtrendingrepos.presentation.viewmodel.BaseViewModel

abstract class BaseActivity<
        VB : ViewBinding,
        VS : BaseViewState,
        VM : BaseViewModel<VS>
        > : AppCompatActivity() {

    protected lateinit var viewBinding: VB

    abstract val viewModel: VM

    abstract fun setViewBinding(layoutInflater: LayoutInflater): VB

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = setViewBinding(layoutInflater)
        setContentView(viewBinding.root)

        viewModel.viewState.observe(this) {

        }
    }
}
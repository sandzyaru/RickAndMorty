package kg.geektech.rickandmorty.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding



abstract class BaseFragment<VB: ViewBinding, VM: BaseViewModel>(@LayoutRes layoutId: Int): Fragment(layoutId) {
    protected abstract val binding: VB
    protected abstract val viewModel: VM
    protected abstract fun inflateViewBinding(inflater: LayoutInflater): VB


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkInternet()
        initView()
        initViewModel()
        initListener()
    }

    open fun initView() {} //вьюшки
    open fun initListener() {} //логика кликов
    open fun initViewModel() {} //обзерверы
    open fun checkInternet() {}



}
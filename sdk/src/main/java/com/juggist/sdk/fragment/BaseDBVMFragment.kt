package com.juggist.sdk.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.juggist.sdk.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType
/**
 * 使用 databinding & viewModel 模式绑定activity
 * 需要注入databinding & viewModel 类型
 */
abstract class BaseDBVMFragment<VM: BaseViewModel,DB: ViewDataBinding>(val layoutId:Int,):Fragment() {
    protected lateinit var rootView: View
    protected lateinit var dataBinding: DB
    protected lateinit var viewModel: VM
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.setContentView(requireActivity(),layoutId)
        dataBinding.lifecycleOwner = this
        rootView = dataBinding.root
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(getVmClazz(this))
    }

    private fun <VM> getVmClazz(obj: Any): VM {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
    }
}
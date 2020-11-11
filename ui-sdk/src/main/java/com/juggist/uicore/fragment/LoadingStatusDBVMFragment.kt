package com.juggist.uicore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.juggist.sdk.fragment.BaseDBVMFragment
import com.juggist.uicore.R
import com.juggist.uicore.databinding.FragmentLoadingStatusDbvmBinding
import com.juggist.uicore.viewmodel.LoadingStatusViewModel
import kotlinx.android.synthetic.main.fragment_loading_status_dbvm.*

abstract class LoadingStatusDBVMFragment<VM: LoadingStatusViewModel,DB : ViewDataBinding>(private val childLayoutId: Int, private val showLoading:Boolean = false, private val showContent:Boolean = false):BaseDBVMFragment<VM, FragmentLoadingStatusDbvmBinding>(
    R.layout.fragment_loading_status_dbvm) {

    protected lateinit var loadingChildDataBind : DB
    protected lateinit var loadingChildView : View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //初始化子view的databind
        loadingChildDataBind = DataBindingUtil.inflate(LayoutInflater.from(requireContext()),childLayoutId,null,false)
        loadingChildDataBind.lifecycleOwner = this
        //通过databind,获取子view对象
        loadingChildView = loadingChildDataBind.root

        //把子view添加到当前父容器中
        if (loading_status_content_view.childCount > 0) {
            loading_status_content_view.removeAllViews()
        }
        loading_status_content_view.addView(loadingChildView)

        initData()
    }

    open fun initData(){
        //通过databind绑定数据(loaidng&status的数据)
        if(showLoading){
            showLoading(showContent)
            startLoadingAction()
        }else{
            hideLoading()
        }
    }

    /**
     * 展示loading
     */
    fun showLoading(contentShow:Boolean) {
//        viewModel.showLoading(contentShow)
    }

    /**
     * 隐藏loading&展示status
     */
    fun hideLoading() {
        hideLoading(false)
    }

    fun hideLoading(showStatus: Boolean) {
        hideLoading("",showStatus)
    }

    fun hideLoading(title: String,showStatus: Boolean) {
        hideLoading(title,  "",showStatus)
    }

    fun hideLoading(title: String, btnStr: String, showStatus: Boolean) {
        hideLoading(title, 0, btnStr,showStatus)
    }

    fun hideLoading(title: String, icon: Int, btnStr: String, showStatus: Boolean) {
        viewModel.hideLoading(title,icon,btnStr,showStatus)
    }

    /**
     * 点击按钮重试
     */
    fun btnRetry(view:View){
        showLoading(false)
        startLoadingAction()
    }


    /**
     * 触发初始化行为
     */
    abstract fun startLoadingAction()
}
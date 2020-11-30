package com.juggist.uicore.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.juggist.uicore.R
import com.juggist.uicore.databinding.ActivityLoadingStatusDbvmBinding
import com.juggist.uicore.viewmodel.LoadingStatusViewModel
import kotlinx.android.synthetic.main.activity_loading_status_dbvm.*

/**
* loading&status
* 展示&隐藏loadingView
* 显示结果statusView
*/
abstract class LoadingStatusDBVMActivity<VM: LoadingStatusViewModel,DB : ViewDataBinding>(private val childLayoutId: Int, private val showLoading:Boolean = false, private val showContent:Boolean = false):
    NavigationBarDBVMActivity<VM, ActivityLoadingStatusDbvmBinding>(
    R.layout.activity_loading_status_dbvm) {

    protected lateinit var loadingChildDataBind : DB
    protected lateinit var loadingChildView : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化子view的databind
        loadingChildDataBind = DataBindingUtil.inflate(LayoutInflater.from(this),childLayoutId,null,false)
        loadingChildDataBind.lifecycleOwner = this
        //通过databind,获取子view对象
        loadingChildView = loadingChildDataBind.root

        //把子view添加到当前父容器中
        if (loading_status_content_view.childCount > 0) {
            loading_status_content_view.removeAllViews()
        }
        loading_status_content_view.addView(loadingChildView)
        initLoadingConfiguration()
        initData()
    }

    open fun initData(){
        //通过databind绑定数据(loaidng&status的数据)
        navChildDataBind.vm = viewModel
        if(showLoading){
            showLoading(showContent)
            startLoadingAction()
        }else{
            hideLoading()
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    /**
     * 展示loading
     */
    fun showLoading(contentShow:Boolean) {
        viewModel.showLoading(contentShow)
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
     * 初始化配置
     */
    abstract fun initLoadingConfiguration()

    /**
     * 触发初始化行为
     */
    abstract fun startLoadingAction()


    final override fun initNavigationConfiguration() {
        //不做实现
    }
}

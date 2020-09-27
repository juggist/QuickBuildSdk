package com.juggist.uicore.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.juggist.uicore.R
import kotlinx.android.synthetic.main.activity_loading_status.*

/**
 * loading&status
 * 展示&隐藏loadingView
 * 显示结果statusView
 */
abstract class LoadingStatusActivity constructor(private val childLayoutId: Int,private val showLoading:Boolean = true) : NavigationBarActivity(R.layout.activity_loading_status) {
    //showloaidng : 默认是展示loading状态

    protected lateinit var loadingStatusRootView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingStatusRootView = LayoutInflater.from(this).inflate(childLayoutId, null)
        if (loading_status_content_view.childCount > 0) {
            loading_status_content_view.removeAllViews()
        }
        loading_status_content_view.addView(loadingStatusRootView)

        if(showLoading){
            showLoading()
            startLoadingAction()
        }else{
            hideLoading()
        }

    }

    /**
     * 展示loading
     */
    fun showLoading() {
        loading_status_content_view.visibility = GONE
        ll_status.visibility = FrameLayout.GONE
        cv_loading.visibility = FrameLayout.VISIBLE
        Glide.with(this).load(R.drawable.gf_loading).into(iv_loading)
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

        tv_status.text = title
        Glide.with(this).load(icon).into(iv_status)
        tv_retry.text = btnStr
        tv_retry.setOnClickListener {
            showLoading()
            startLoadingAction()
        }

        loading_status_content_view.visibility = if (showStatus) FrameLayout.GONE else FrameLayout.VISIBLE
        ll_status.visibility = if (showStatus) FrameLayout.VISIBLE else FrameLayout.GONE
        cv_loading.visibility = FrameLayout.GONE
        Glide.with(this).load(0).into(iv_loading)
    }

    /**
     * 触发初始化行为
     */
    abstract fun startLoadingAction()
}
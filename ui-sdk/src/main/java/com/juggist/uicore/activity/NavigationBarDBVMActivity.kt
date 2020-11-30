package com.juggist.uicore.activity

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.juggist.sdk.activity.BaseDBVMActivity
import com.juggist.uicore.R
import com.juggist.uicore.databinding.ActivityNavigationBarDbvmBinding
import com.juggist.uicore.viewmodel.NavigationViewModel
import kotlinx.android.synthetic.main.activity_navigation_bar_dbvm.navigation_bar_content_view
import kotlinx.android.synthetic.main.activity_navigation_bar_dbvm.fake_bar
import kotlinx.android.synthetic.main.activity_navigation_bar_dbvm.navigation_bar
import kotlinx.android.synthetic.main.activity_navigation_bar_dbvm.left_back

/**
 * 1.自定义顶部导航栏activity
 *
 * 2.父布局为:R.layout.activity_navigation_bar
 *  childLayoutId:需要显示的内容，属于父布局的子view
 *  fullScreen:布局是否覆盖导航栏部分
 *
 * 3.navigationViewModel:包含导航栏需要的配置信息
 *
 * 4.派生类只能使用 childDataBinding 去绑定 viewmodel
 *  派生类的布局是 childRootView
 *
 *
 */
abstract class NavigationBarDBVMActivity<VM : NavigationViewModel, DB : ViewDataBinding>(
    private val childLayoutId: Int,
    private val fullScreen: Boolean = false
) : BaseDBVMActivity<VM, ActivityNavigationBarDbvmBinding>(
    R.layout.activity_navigation_bar_dbvm
) {
    protected lateinit var navChildDataBind: DB
    protected lateinit var navChildView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化子view的databind
        navChildDataBind =
            DataBindingUtil.inflate(LayoutInflater.from(this), childLayoutId, null, false)
        navChildDataBind.lifecycleOwner = this
        //通过databind,获取子view对象
        navChildView = navChildDataBind.root

        //把子view添加到当前父容器中
        if (navigation_bar_content_view.childCount > 0)
            navigation_bar_content_view.removeAllViews()
        navigation_bar_content_view.addView(navChildView)

        initNavigationConfiguration()
        initListener()
        initData()
    }

    private fun initListener() {
        left_back.setOnClickListener {
            this.finish()
        }
    }

    private fun initData() {
        navigation_bar.setBackgroundColor(if (fullScreen) Color.TRANSPARENT else Color.WHITE)
        fake_bar.visibility = if (fullScreen) View.GONE else View.VISIBLE
        val pageTitle = setNavigationTitie()
        if (null == pageTitle) viewModel.setNavigationShow(false)
        else {
            viewModel.setNavigationShow(true)
            viewModel.setNavigationTitie(pageTitle)
        }

        //通过databind绑定数据(导航栏上的信息,未来可拓展)
        rootChildDataBind.navigationViewModel = viewModel

    }

    //设置标题
    abstract fun setNavigationTitie(): String?

    abstract fun initNavigationConfiguration()
}
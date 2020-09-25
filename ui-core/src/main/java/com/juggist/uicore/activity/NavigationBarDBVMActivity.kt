package com.juggist.uicore.activity

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.juggist.sdk.activity.BaseDBVMActivity
import com.juggist.sdk.viewmodel.BaseViewModel
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
abstract class NavigationBarDBVMActivity<VM : BaseViewModel,DB : ViewDataBinding>(private val childLayoutId: Int, private val fullScreen:Boolean = false) : BaseDBVMActivity<VM, ActivityNavigationBarDbvmBinding>(R.layout.activity_navigation_bar_dbvm) {
    protected lateinit var childDB : DB
    protected lateinit var childView : View
    private lateinit var navigationVM : NavigationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        childDB = DataBindingUtil.inflate(LayoutInflater.from(this),childLayoutId,null,false)
        childDB.lifecycleOwner = this
        childView = childDB.root
        if(navigation_bar_content_view.childCount > 0)
            navigation_bar_content_view.removeAllViews()
        navigation_bar_content_view.addView(childView)
        initListener()
        initData()
    }

    private fun initListener(){
        left_back.setOnClickListener {
            this.finish()
        }
    }
    private fun initData(){
        navigation_bar.setBackgroundColor(if(fullScreen) Color.TRANSPARENT else Color.WHITE)
        fake_bar.visibility = if (fullScreen) View.GONE else View.VISIBLE

        navigationVM = ViewModelProvider(this).get(NavigationViewModel::class.java)
        rootDB.navigationViewModel = navigationVM

    }

    //设置标题
    fun setNavigationTitie(title:String){
        navigationVM.title.postValue(title)
    }

}
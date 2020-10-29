package com.juggist.uicore.activity

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.juggist.sdk.activity.BaseActivity
import com.juggist.uicore.R
import kotlinx.android.synthetic.main.activity_navigation_bar.*

abstract class NavigationBarActivity(private val childLayoutId:Int, private val fullScreen:Boolean = false):BaseActivity(R.layout.activity_navigation_bar) {
    protected lateinit var navigationBarRootView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationBarRootView = LayoutInflater.from(this).inflate(childLayoutId,null)
        if(navigation_bar_content_view.childCount > 0){
            navigation_bar_content_view.removeAllViews()
        }
        navigation_bar_content_view.addView(navigationBarRootView)

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
    }

    //设置标题
    fun setNavigationTitle(title:String){
        tv_title.text = title
    }
}
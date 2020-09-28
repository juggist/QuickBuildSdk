package com.juggist.uicore.activity

import android.os.Bundle
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.juggist.sdk.activity.BaseActivity
import com.juggist.uicore.R
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

/**
 * 底部导航栏切换容器
 * 1:实现导航栏组件menu
 * 2:实现fragment集合
 */
abstract class BottomNavgationActivity(private val userInputEnable:Boolean = false) : BaseActivity(R.layout.activity_bottom_navigation) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
        initListener()
    }

    private fun initView() {
        bottom_nav.inflateMenu(getMenuId())
        vp_fragments.adapter = NavigationPageAdapter(this)
        //设置viewPager2是否可以手势滑动
        vp_fragments.isUserInputEnabled = userInputEnable
        //设置最大保存的fragment数量，这样fragment不会被摧毁。
        vp_fragments.offscreenPageLimit = getFragment().size

    }

    private fun initData() {

    }

    private fun initListener() {
        //注册bottomNavigation点击事件 & 注册viewPager2的切换时间
        //并对2个组件进行事件绑定
        bottom_nav.setOnNavigationItemSelectedListener {
            bottom_nav.menu.forEachIndexed { index, item ->
                if(it == item){
                    vp_fragments.setCurrentItem(index,false)
                    return@forEachIndexed
                }
            }
            false
        }
        vp_fragments.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottom_nav.menu.getItem(position).isChecked = true
            }
        })
    }

    /**
     * 获取menu
     */
    abstract fun getMenuId(): Int

    /**
     * 获取需要展示的fragment集合
     */
    abstract fun getFragment(): List<Fragment>

    inner class NavigationPageAdapter(context: FragmentActivity) : FragmentStateAdapter(context) {
        override fun getItemCount() = getFragment().size

        override fun createFragment(position: Int): Fragment {
            return getFragment()[position]
        }

    }

}
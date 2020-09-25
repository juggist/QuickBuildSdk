package com.juggist.uicore.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_list_refresh.*

/**
 * 只做列表显示
 * 如果数据为空/异常，可以点击statusView触发loading行为
 * 无refresh&loadmore行为
 */
abstract class ListActivity<T> :ListRefreshActivity<T>(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        refresh_layout.setEnableRefresh(false)
        refresh_layout.setEnableLoadMore(false)
    }

    final override fun startLoadMoreAction() {

    }

    final override fun startRefreshAction() {

    }
}
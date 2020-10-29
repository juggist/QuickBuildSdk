package com.juggist.uicore.fragment

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_list_refresh.*

/**
 * 只做列表显示
 * 如果数据为空/异常，可以点击statusView触发loading行为
 * 无refresh&loadmore行为
 */
abstract class ListFragment<T> :ListRefreshFragment<T>(){
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        refresh_layout.setEnableLoadMore(false)
        refresh_layout.setEnableRefresh(false)
    }

    final override fun startLoadMoreAction() {
    }

    final override fun startRefreshAction() {

    }
}
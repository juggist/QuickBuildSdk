package com.juggist.uicore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.juggist.sdk.fragment.BaseFragment
import com.juggist.uicore.R
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.simple.SimpleMultiListener
import kotlinx.android.synthetic.main.activity_list_refresh.*
/**
 * 1.listview刷新fragment,内部实现了smartRefreshLayout
 * 2.派生类需要实现:
 *      (1)adapter
 *      (2)刷新启动事件
 *      (3)加载更多启动事件
 * 3.构造参数 "autoLoading" 是否进入页面自动刷新，默认否
 */
abstract class ListRefreshFragment<T>(var autoLoading:Boolean = false):LoadingStatusFragment(R.layout.fragment_list_refresh,showLoading = !autoLoading) {
    //状态占位视图（空数据/异常)
    protected lateinit var statusView : View
    protected var adapter : BaseQuickAdapter<T,BaseViewHolder> = getListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListView()
        initRefreshLayout()
        initEmptyView()
    }

    /**
     * 初始化listview
     */
    private fun initListView(){
        list_view.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        list_view.adapter = adapter
    }

    /**
     * 初始化刷新控件
     */
    private fun initRefreshLayout(){
        if(autoLoading)
            refresh_layout.autoRefresh()

        refresh_layout.setOnMultiListener(object : SimpleMultiListener(){
            override fun onRefresh(refreshLayout: RefreshLayout) {
                super.onRefresh(refreshLayout)
                startRefreshAction()
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                super.onLoadMore(refreshLayout)
                startLoadMoreAction()
            }
        })
    }

    /**
     * 初始化空数据视图
     */
    private fun initEmptyView(){
        setEmptyView(R.layout.layout_empty)
    }

    protected fun setEmptyView(layoutId:Int){
        setEmptyView(LayoutInflater.from(requireContext()).inflate(layoutId,null))
    }

    protected fun setEmptyView(emptyDataView:View){
        this.statusView = emptyDataView
        adapter.setEmptyView(emptyDataView)
        adapter.isUseEmpty = true
        //如果数据为空/异常 点击statusView可以重新发起请求
        this.statusView.setOnClickListener {
            startRefreshAction()
        }
    }

    /**
     * 刷新&加载更多结束
     */
    protected fun refreshFinish(){
        refresh_layout.finishRefresh()
    }

    protected fun refreshFinishNoMoreData(){
        refresh_layout.finishRefreshWithNoMoreData()
    }

    protected fun loadMoreFinish(){
        refresh_layout.finishLoadMore()
    }
    protected fun loadMoreFinishNoMoreData(){
        refresh_layout.finishLoadMoreWithNoMoreData()
    }


    protected abstract fun getListAdapter(): BaseQuickAdapter<T, BaseViewHolder>

    /**
     * 触发刷新行为
     */
    protected abstract fun startRefreshAction()

    /**
     * 触发加载更多行为
     */
    protected abstract fun startLoadMoreAction()
}
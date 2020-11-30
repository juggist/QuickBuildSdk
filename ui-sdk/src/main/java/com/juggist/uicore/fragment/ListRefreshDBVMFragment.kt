package com.juggist.uicore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.juggist.uicore.R
import com.juggist.uicore.databinding.FragmentListRefreshDbvmBinding
import com.juggist.uicore.viewmodel.ListRefreshViewModel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.simple.SimpleMultiListener
import kotlinx.android.synthetic.main.fragment_list_refresh_dbvm.*

/**
 * 1.listview刷新fragment,内部实现了smartRefreshLayout
 * 2.派生类需要实现:
 *      (1)adapter
 *      (2)刷新启动事件
 *      (3)加载更多启动事件
 * 3.构造参数 "autoLoading" 是否进入页面自动刷新，默认否
 * 4.headerLayoutId为listview顶部布局
 */
abstract class ListRefreshDBVMFragment<VM: ListRefreshViewModel,DB:ViewDataBinding,T>(private val headerLayoutId :Int? = null, var autoLoading:Boolean = false) : LoadingStatusDBVMFragment<VM, FragmentListRefreshDbvmBinding>(
    R.layout.fragment_list_refresh_dbvm){

    protected lateinit var listRefreshHeaderDataBind : DB
    protected lateinit var listRefreshHeaderView : View
    //状态占位视图（空数据/异常)
    protected lateinit var statusView : View
    protected var adapter : BaseQuickAdapter<T,BaseViewHolder> = getListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        headerLayoutId?.let {
            //初始化子view的databind
            listRefreshHeaderDataBind = DataBindingUtil.inflate(LayoutInflater.from(requireContext()),headerLayoutId,null,false)
            listRefreshHeaderDataBind.lifecycleOwner = this
            //通过databind,获取子view对象
            listRefreshHeaderView = listRefreshHeaderDataBind.root
            //把子view添加到当前父容器中
            if (fl_header.childCount > 0) {
                fl_header.removeAllViews()
            }
            fl_header.addView(listRefreshHeaderView)
        }
        initListRefreshChildConfiguration()
        initListView()
        initRefreshLayout()
        initEmptyView()
        initVM()
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
            autoRefresh()

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

    fun autoRefresh(){
        refresh_layout.autoRefresh()
    }
    private fun initVM(){
        viewModel.refreshFinish.observe(viewLifecycleOwner,
            Observer<Boolean> { t ->
                t?.let {
                    if(t)
                        refreshFinish()
                }
            })
        viewModel.refreshFinishNoMoreData.observe(viewLifecycleOwner,
            Observer<Boolean> { t ->
                t?.let {
                    if(t)
                        refreshFinishNoMoreData()
                }
            })
        viewModel.loadMoreFinish.observe(viewLifecycleOwner,
            Observer<Boolean> { t ->
                t?.let {
                    if(t)
                        loadMoreFinish()
                }
            })
        viewModel.loadMoreFinishNoMoreData.observe(viewLifecycleOwner,
            Observer<Boolean> { t ->
                t?.let {
                    if(t)
                        loadMoreFinishNoMoreData()
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
    private fun refreshFinish(){
        refresh_layout.finishRefresh()
    }

    private fun refreshFinishNoMoreData(){
        refresh_layout.finishRefreshWithNoMoreData()
    }

    private fun loadMoreFinish(){
        refresh_layout.finishLoadMore()
    }
    private fun loadMoreFinishNoMoreData(){
        refresh_layout.finishLoadMoreWithNoMoreData()
    }

    protected abstract fun initListRefreshChildConfiguration()

    protected abstract fun getListAdapter(): BaseQuickAdapter<T, BaseViewHolder>

    /**
     * 触发刷新行为
     */
    protected abstract fun startRefreshAction()

    /**
     * 触发加载更多行为
     */
    protected abstract fun startLoadMoreAction()




    final override fun startLoadingAction() {
        //不做任何实现
    }

    final override fun initConfiguration() {
        //不做任何实现
    }


}
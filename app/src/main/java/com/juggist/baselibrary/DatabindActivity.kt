package com.juggist.baselibrary

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.juggist.baselibrary.databinding.ActivityDatabindBinding
import com.juggist.baselibrary.databinding.AdapterItemBinding
import com.juggist.uicore.activity.ListActivity
import com.juggist.uicore.activity.ListRefreshActivity
import com.juggist.uicore.activity.NavigationBarActivity
import com.juggist.uicore.activity.NavigationBarDBVMActivity
import kotlinx.android.synthetic.main.activity_databind.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//class DatabindActivity : NavigationBarActivity(R.layout.activity_databind,fullScreen = true) {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        Log.i("Juggist","start")
//        var job = lifecycleScope.launch(context = Dispatchers.Main){
//            delay(1000)
//            Log.i("Juggist","launch")
//            tv.text = "launch"
//        }
////        Log.i("Juggist","end")
////        childDataBinding.bindViewModel = viewModel
////        viewModel.age.observe(this){
////            Log.i(TAG,"age = $it")
////        }
////        viewModel.age.value = "哈哈哈"
//        var i = 0;
//        tv.setOnClickListener {
//            if(i % 2 == 0){
////                showLoadingDialog()
//            }else{
////                dismissLoadingDialog()
//            }
//            i++
////            Log.i(TAG,"viewModel : $viewModel")
////            viewModel.age.value = "布鲁布鲁"
////            viewModel.getName()
////            setNavigationTitie("我要变色了")
//        }
//
//
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
////        Log.i("Juggist","onDestory")
//    }
//
//}
class DatabindActivity:ListActivity<String>(){

    override fun getListAdapter(): BaseQuickAdapter<String, BaseViewHolder> {
        return object : BaseQuickAdapter<String,BaseViewHolder>(R.layout.adapter_item, mutableListOf("1","2","3","4","5")){
            override fun convert(holder: BaseViewHolder, item: String) {
                val binding = DataBindingUtil.bind<AdapterItemBinding>(holder.itemView)
                binding?.name = item
                binding?.executePendingBindings()
            }

            override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
                DataBindingUtil.bind<AdapterItemBinding>(viewHolder.itemView)
            }

        }
    }

//    override fun startRefreshAction() {
//        lifecycleScope.launch(Dispatchers.Main){
//            delay(3000)
//            refreshFinish()
//        }
//    }
//
//    override fun startLoadMoreAction() {
//    }

    override fun startLoadingAction() {
        lifecycleScope.launch(Dispatchers.Main){
            delay(3000)
//            hideLoading()
            hideLoading("空数据",true)
        }
    }

//    override fun startRefresh() {
//
//    }
//
//    override fun startLoadMore() {
//
//    }
}
//class DatabindActivity : BaseActivity(R.layout.activity_fragment){
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        supportFragmentManager.beginTransaction().add(R.id.fl_content,DataFragment()).commit()
//
//    }
//    class DataFragment:ListFragment<String>(){
//        override fun onActivityCreated(savedInstanceState: Bundle?) {
//            super.onActivityCreated(savedInstanceState)
//            setEmptyView(R.layout.layout_error)
//        }
//        override fun getListAdapter(): BaseQuickAdapter<String, BaseViewHolder> {
//            return object : BaseQuickAdapter<String,BaseViewHolder>(R.layout.adapter_item){
//                override fun convert(holder: BaseViewHolder, item: String) {
//                    val binding = DataBindingUtil.bind<AdapterItemBinding>(holder.itemView)
//                    binding?.name = item
//                    binding?.executePendingBindings()
//                }
//
//                override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
//                    DataBindingUtil.bind<AdapterItemBinding>(viewHolder.itemView)
//                }
//
//            }
//        }
//
//        override fun startRefreshAction() {
//            lifecycleScope.launch {
//                delay(3000)
//                adapter.setNewInstance(mutableListOf("a","b"))
//            }
//        }
//
////        override fun startRefresh() {
////            lifecycleScope.launch {
////                delay(3000)
////                refreshFinish()
////                adapter.addHeaderView(LayoutInflater.from(requireContext()).inflate(R.layout.item,null))
////                adapter.setNewInstance(mutableListOf("a","b"))
////            }
////        }
////
////        override fun startLoadMore() {
////            lifecycleScope.launch {
////                delay(3000)
////                loadMoreFinishNoMoreData()
////                adapter.addData(mutableListOf("1","2"))
////            }
////        }
//
//    }
//}
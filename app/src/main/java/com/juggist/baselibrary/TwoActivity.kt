package com.juggist.baselibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.juggist.baselibrary.databinding.AdapterItemBinding
import com.juggist.uicore.fragment.ListFragment
import com.juggist.uicore.fragment.ListRefreshFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TwoActivity : AppCompatActivity() {
//    val vm by lazy { ViewModelLazy<TwoViewModel>() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        supportFragmentManager.beginTransaction().replace(R.id.parent,MyFragment()).commit()
//        val vm = ViewModelProvider(this).get(TwoViewModel::class.java)

    }

    override fun onDestroy() {
        super.onDestroy()
//        viewModelStore.clear()
    }
    class MyFragment : ListFragment<String>(){
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
//
//        override fun startRefreshAction() {
//            lifecycleScope.launch(Dispatchers.Main){
//                delay(3000)
//                refreshFinish()
//            }
//        }
//
//        override fun startLoadMoreAction() {
//        }

        override fun startLoadingAction() {
            lifecycleScope.launch(Dispatchers.Main){
                delay(3000)
            hideLoading()
//                hideLoading("空数据","重试") {
//                    startLoadingAction()
//                }
            }
        }

    }
}
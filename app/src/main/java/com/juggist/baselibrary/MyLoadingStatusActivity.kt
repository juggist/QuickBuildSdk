package com.juggist.baselibrary

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.juggist.baselibrary.databinding.ActivityMyLoadingStatusBinding
import com.juggist.uicore.activity.LoadingStatusDBVMActivity
import kotlinx.android.synthetic.main.activity_my_loading_status.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyLoadingStatusActivity : LoadingStatusDBVMActivity<LoadingStatusVM,ActivityMyLoadingStatusBinding>(R.layout.activity_my_loading_status,showLoading = false) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingChildDataBind.vm = viewModel
        tv_time.setOnClickListener {
            startLoadingAction()
        }
    }
    override fun startLoadingAction() {
        lifecycleScope.launch(Dispatchers.Main){
            delay(3000)
            viewModel.updateTime("22222")
            hideLoading("数据失败","请重新尝试",true)
            setNavigationTitie("数据失败页面")
        }
    }
}
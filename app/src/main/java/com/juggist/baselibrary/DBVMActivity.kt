package com.juggist.baselibrary

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.juggist.baselibrary.databinding.ActivityDbvmBinding
import com.juggist.sdk.activity.BaseDBVMActivity
import kotlinx.android.synthetic.main.activity_dbvm.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DBVMActivity : BaseDBVMActivity<DBViewModel,ActivityDbvmBinding>(R.layout.activity_dbvm) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.Main){
            delay(3000)
            viewModel.name .value = "helen"
        }
        rootChildDataBind.vm = viewModel
        tv.setOnClickListener {
            viewModel.title.value = "主题"
        }
    }
}
package com.juggist.baselibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider

class TwoActivity : AppCompatActivity() {
//    val vm by lazy { ViewModelLazy<TwoViewModel>() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        val vm = ViewModelProvider(this).get(TwoViewModel::class.java)

    }

    override fun onDestroy() {
        super.onDestroy()
//        viewModelStore.clear()
    }
}
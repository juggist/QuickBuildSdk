package com.juggist.baselibrary

import android.util.Log
import com.cqteam.networklib.NetWorkConfig
import com.cqteam.networklib.NetWorkManager
import com.cqteam.networklib.`interface`.LoadingProvider
import com.cqteam.networklib.`interface`.LoggerProvider
import com.cqteam.networklib.`interface`.ToastProvider
import com.juggist.sdk.BaseApplication

class MyApplication : BaseApplication(BuildConfig.APPLICATION_ID) {
    override fun onCreate() {
        super.onCreate()
        val juggConfig = NetWorkConfig.Builder(this).addLoadingProvider(object :LoadingProvider{
            override fun showLoading() {
                Log.i("Juggist","loading show : thread Name = ${Thread.currentThread().name}")
            }

            override fun dismissLoading() {
                Log.i("Juggist","loading dismiss : thread Name = ${Thread.currentThread().name}")
            }

        }).addToastProvider(object :ToastProvider{
            override fun toast(str: String?) {
                Log.i("Juggist","toast  : thread Name = ${Thread.currentThread().name} ; message = $str")
            }

        }).addLogger(object :LoggerProvider{
            override fun log(message: String) {
//                Log.i("Juggist","log  : thread Name = ${Thread.currentThread().name} ; message =  $message")
            }

        }).build()
//        NetWorkManager.init(juggConfig,"")
        NetWorkManager.init(juggConfig,"https://api.coincap.io/v2/")
    }
}
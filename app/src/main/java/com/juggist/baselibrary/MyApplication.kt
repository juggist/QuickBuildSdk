package com.juggist.baselibrary

import android.util.Log
import com.cqteam.networklib.NetWorkConfig
import com.cqteam.networklib.NetWorkManager
import com.cqteam.networklib.`interface`.LoadingProvider
import com.cqteam.networklib.`interface`.LoggerProvider
import com.cqteam.networklib.`interface`.ParamsProvider
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

        }).addParamsProvider(object :ParamsProvider{
            override fun bodyParams(): HashMap<String, String>? {
                return null
            }

            override fun headerParams(): HashMap<String, String>? {
                return hashMapOf("ticket" to "852e6cde69824177935cfd497c28aedd","Content-Type" to "application/json")
            }

        }).build()
//        NetWorkManager.init(juggConfig,"")
        NetWorkManager.init(juggConfig,"http://mgr-api-test.jronline.com/")
//        RetrofitUtil.initBaseUrl("http://mgr-api-test.jronline.com/")
    }
}
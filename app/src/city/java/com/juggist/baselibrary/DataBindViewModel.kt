package com.juggist.baselibrary

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cqteam.networklib.NetWorkManager
import com.juggist.sdk.libs.http.NetWorkHanlder
import com.juggist.sdk.libs.http.NetWorkListener
import com.juggist.sdk.viewmodel.BaseViewModel
import com.juggist.uicore.viewmodel.NavigationViewModel
import kotlinx.coroutines.*

class DataBindViewModel : BaseViewModel() {
    val age :MutableLiveData<String> = MutableLiveData("第一次")
    val apiService = NetWorkManager.getRetrofit().create(ApiService::class.java)
    var job : Job?= null
    fun getName() {

//        viewModelScope.launch {
//            suspendCancellableCoroutine {
//                it.invokeOnCancellation {
//
//                }
//            }
//        }
        job = NetWorkHanlder.doJob(viewModelScope, {
            apiService.getName()
        }, object : NetWorkListener<PriceBean> {
            override fun onSuccess(result: PriceBean) {
                Log.i("Juggist","onSuccess = $result ; ${Thread.currentThread().name}")
            }

            override fun onStart() {
                super.onStart()
                Log.i("Juggist","onStart  ; ${Thread.currentThread().name}")
            }

            override fun onFail(errCode: Int, errMes: String) {
                super.onFail(errCode, errMes)
                Log.i("Juggist","onFail ; ${Thread.currentThread().name}")
            }

            override fun onFinish() {
                super.onFinish()
                Log.i("Juggist","onFinish  ; ${Thread.currentThread().name}")
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
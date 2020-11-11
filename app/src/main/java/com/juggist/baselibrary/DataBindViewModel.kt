package com.juggist.baselibrary

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cqteam.networklib.NetWorkManager
import com.juggist.sdk.libs.http.NetWorkHandler
import com.juggist.sdk.libs.http.NetWorkListener
import com.juggist.sdk.viewmodel.BaseViewModel
import kotlinx.coroutines.Job
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody


class DataBindViewModel : BaseViewModel() {
    val age :MutableLiveData<String> = MutableLiveData("第一次")
    val apiService = NetWorkManager.create(ApiService::class.java)
    var job : Job?= null
    fun getName() {

//        viewModelScope.launch {
//            suspendCancellableCoroutine {
//                it.invokeOnCancellation {
//
//                }
//            }
//        }
        Log.i("Juggist","request start")
//        NetWorkHandler.doJob(viewModelScope,{
//            apiService.getWeekScheduleTeacherInfo(
////                "2020-11-7", "2020-11-15",
//                parseBody(hashMapOf("startDate" to "2020-11-7","endDate" to "2020-11-15")),
//                hashMapOf("ticket" to "852e6cde69824177935cfd497c28aedd","Content-Type" to "application/json; charset=utf-8"))
//        },object : NetWorkListener<ResultTest>{
//            override fun onSuccess(result: ResultTest) {
//                Log.i("Juggist","onSuccess = $result ; ${Thread.currentThread().name}")
//            }
//
//        })
        NetWorkHandler.doJob(viewModelScope,{
            apiService.getCourseScheduleByDate(parseBody(hashMapOf("startDate" to "2020-11-7","endDate" to "2020-11-15")), hashMapOf())
        },object :NetWorkListener<ResultTest>{
            override fun onSuccess(result: ResultTest) {
            }

        })


//        job = NetWorkHandler.doJob(viewModelScope, {
//            apiService.getName()
//        }, object : NetWorkListener<PriceBean> {
//            override fun onSuccess(result: PriceBean) {
//                Log.i("Juggist","onSuccess = $result ; ${Thread.currentThread().name}")
//            }
//
//            override fun onStart() {
//                super.onStart()
//                Log.i("Juggist","onStart  ; ${Thread.currentThread().name}")
//            }
//
//            override fun onFail(errCode: Int, errMes: String) {
//                super.onFail(errCode, errMes)
//                Log.i("Juggist","onFail ; ${Thread.currentThread().name}")
//            }
//
//            override fun onFinish() {
//                super.onFinish()
//                Log.i("Juggist","onFinish  ; ${Thread.currentThread().name}")
//            }
//        })
        Log.i("Juggist","request end")
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
    fun parseBody(hashMap: HashMap<String,String>):RequestBody{
        val data = StringBuffer()
        if(hashMap.size > 0){
            val iterator = hashMap.iterator()
            while(iterator.hasNext()){
                val next = iterator.next()
                data.append(next.key).append("=").append(next.value).append("&")
            }

        }
        val json = data.substring(0,data.length - 1)
        return RequestBody.create("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull(),json)
    }
}
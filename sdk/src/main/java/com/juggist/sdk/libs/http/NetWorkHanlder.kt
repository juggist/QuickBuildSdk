package com.juggist.sdk.libs.http

import com.cqteam.networklib.NetWorkManager
import kotlinx.coroutines.*

/**
 * 发起网络请求
 */
object NetWorkHanlder {

    fun <T> doJob(scope: CoroutineScope, block: suspend CoroutineScope.() -> T, listener: NetWorkListener<T>):Job {
        return doJob(scope, false ,block, listener)
    }
    /**
     * scope:自定义协程域，如果是viewModelScope，则不需要手动关闭
     * 如果是GlobalScope，则需要拿返回的job，在适当的时机关闭协程
     * showLoading:是否展示全局的loading
     */
    fun <T> doJob(scope: CoroutineScope, showLoading: Boolean, block: suspend CoroutineScope.() -> T, listener: NetWorkListener<T>):Job {
        //所有的状态切到主线程
        return scope.launch(Dispatchers.Main) {
            if (showLoading) {
                NetWorkManager.getConfig().loadingProvider?.showLoading()
            }
            listener?.onStart()
            //请求过程异步实现
            val result = withContext(Dispatchers.IO) {
                delay(3000)
                block.invoke(this)
            }
            if (result is T) {
                listener?.onSuccess(result)
            } else {
                listener?.onFail(-99, "<T> type is not match")
            }
            if (showLoading) {
                NetWorkManager.getConfig().loadingProvider?.dismissLoading()
            }
            listener?.onFinish()
        }
    }
}
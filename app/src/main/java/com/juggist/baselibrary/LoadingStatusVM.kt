package com.juggist.baselibrary

import androidx.lifecycle.MutableLiveData
import com.juggist.uicore.viewmodel.LoadingStatusViewModel

class LoadingStatusVM:LoadingStatusViewModel() {
    var time:MutableLiveData<String> = MutableLiveData("1111")
    fun updateTime(time:String){
        this.time.postValue(time)
    }
}
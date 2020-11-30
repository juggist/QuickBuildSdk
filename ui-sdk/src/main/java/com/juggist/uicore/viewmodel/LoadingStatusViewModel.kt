package com.juggist.uicore.viewmodel

import androidx.lifecycle.MutableLiveData
import com.juggist.uicore.R

open class LoadingStatusViewModel : NavigationViewModel() {
    var showContent: MutableLiveData<Boolean> = MutableLiveData(false)
    var showLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var showStatus: MutableLiveData<Boolean> = MutableLiveData(false)
    var statusTitle: MutableLiveData<String> = MutableLiveData("")
    var statusBtnStr: MutableLiveData<String> = MutableLiveData("")
    var statusBtnVisiable: MutableLiveData<Boolean> = MutableLiveData(true)
    var statusResId: MutableLiveData<Int> = MutableLiveData(0)
    var statusResVisiable: MutableLiveData<Boolean> = MutableLiveData(true)
    var loaidngResId: MutableLiveData<Int> = MutableLiveData(0)

    /**
     * 展示loading
     */
    fun showLoading(contentShow:Boolean){
        showContent.postValue(contentShow)
        showStatus.postValue(false)
        showLoading.postValue(true)
        loaidngResId.postValue(R.drawable.gf_loading)
    }

    /**
     * 隐藏loading&展示status
     */
    fun hideLoading(title: String = "", icon: Int = R.mipmap.ic_empty_data, btnStr: String = "", showStatus: Boolean = false,showBtn:Boolean = true,showRes:Boolean = true) {
        showContent.postValue(!showStatus)
        this.showStatus.postValue(showStatus)
        showLoading.postValue(false)
        statusTitle.postValue(title)
        statusBtnStr.postValue(btnStr)
        statusBtnVisiable.postValue(showBtn)
        statusResId.postValue(icon)
        loaidngResId.postValue(0)
        statusResVisiable.postValue(showRes)
    }

}
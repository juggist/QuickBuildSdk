package com.juggist.uicore.viewmodel

import androidx.lifecycle.MutableLiveData
import com.juggist.uicore.R

open class LoadingStatusViewModel : NavigationViewModel() {
    var showContent: MutableLiveData<Boolean> = MutableLiveData(false)
    var showLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var showStatus: MutableLiveData<Boolean> = MutableLiveData(false)
    var statusTitle: MutableLiveData<String> = MutableLiveData("")
    var statusBtnStr: MutableLiveData<String> = MutableLiveData("")
    var statusResId: MutableLiveData<Int> = MutableLiveData(0)
    var loaidngResId: MutableLiveData<Int> = MutableLiveData(0)

    /**
     * 展示loading
     */
    fun showLoading() {
        showContent.postValue(false)
        showStatus.postValue(false)
        showLoading.postValue(true)
        loaidngResId.postValue(R.drawable.gf_loading)
    }

    /**
     * 隐藏loading&展示status
     */
    fun hideLoading(title: String, icon: Int, btnStr: String, showStatus: Boolean) {
        showContent.postValue(!showStatus)
        this.showStatus.postValue(showStatus)
        showLoading.postValue(false)
        statusTitle.postValue(title)
        statusBtnStr.postValue(btnStr)
        statusResId.postValue(icon)
        loaidngResId.postValue(0)
    }

}
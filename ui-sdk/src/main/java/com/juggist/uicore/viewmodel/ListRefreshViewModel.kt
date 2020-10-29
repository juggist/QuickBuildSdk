package com.juggist.uicore.viewmodel

import androidx.lifecycle.MutableLiveData
import com.juggist.sdk.viewmodel.BaseViewModel

class ListRefreshViewModel<T> :BaseViewModel() {
    val listData:MutableLiveData<List<T>> = MutableLiveData(emptyList())
}
package com.juggist.sdk.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel:ViewModel(){

    override fun onCleared() {
        super.onCleared()
    }
}
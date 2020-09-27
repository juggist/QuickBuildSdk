package com.juggist.baselibrary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.juggist.uicore.viewmodel.NavigationViewModel

class DBViewModel:NavigationViewModel() {
    var name:MutableLiveData<String>  = MutableLiveData("juggist")
}
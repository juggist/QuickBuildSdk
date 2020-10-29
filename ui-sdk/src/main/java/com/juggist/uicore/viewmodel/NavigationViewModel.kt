package com.juggist.uicore.viewmodel

import androidx.lifecycle.MutableLiveData
import com.juggist.sdk.viewmodel.BaseViewModel

open class NavigationViewModel :BaseViewModel(){
    // 导航栏标题
     val title:MutableLiveData<String> = MutableLiveData("标题")

    fun setNavigationTitie(title:String){
        this.title.postValue(title)
    }
}
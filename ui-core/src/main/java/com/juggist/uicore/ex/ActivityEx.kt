package com.juggist.uicore.ex

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner

inline fun <DB> Activity.createDataBind(childLayoutId:Int,childDataBind:DB, childView : View,contentView:ViewGroup,lifecycleOwner: LifecycleOwner){

//    childDataBind = DataBindingUtil.inflate(LayoutInflater.from(this),childLayoutId,null,false)
//    childDataBind.lifecycleOwner = lifecycleOwner
//    childView = childDataBind.root
//
//    if (contentView.childCount > 0) {
//        contentView.removeAllViews()
//    }
//    contentView.addView(childView)
}
package com.juggist.uicore.databindEx

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageViewDataBind {
    @BindingAdapter(value = ["gifResId"],requireAll = true)
    @JvmStatic
    fun bindGifResId(iv:ImageView,gifResId:Int){
        Glide.with(iv).load(gifResId).into(iv)
    }
}
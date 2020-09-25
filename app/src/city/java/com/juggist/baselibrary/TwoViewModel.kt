package com.juggist.baselibrary

import android.util.Log
import androidx.lifecycle.ViewModel

class TwoViewModel : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        Log.i("Juggist","twoViewModel clear")
    }
}
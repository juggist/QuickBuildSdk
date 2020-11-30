package com.juggist.uicore.viewmodel

import com.juggist.sdk.utils.UnPeekLiveData

open class ListRefreshViewModel:LoadingStatusViewModel() {
    val refreshFinish : UnPeekLiveData<Boolean> = UnPeekLiveData(false)
    val refreshFinishNoMoreData : UnPeekLiveData<Boolean> = UnPeekLiveData(false)
    val loadMoreFinish : UnPeekLiveData<Boolean> = UnPeekLiveData(false)
    val loadMoreFinishNoMoreData : UnPeekLiveData<Boolean> = UnPeekLiveData(false)

}
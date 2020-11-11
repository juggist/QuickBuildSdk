package com.juggist.baselibrary

import com.juggist.baselibrary.databinding.TestFragmentBinding
import com.juggist.uicore.fragment.LoadingStatusDBVMFragment

class TestFragment : LoadingStatusDBVMFragment<TestVM,TestFragmentBinding>(R.layout.test_fragment){
    override fun startLoadingAction() {
    }


}
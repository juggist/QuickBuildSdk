package com.juggist.baselibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.juggist.uicore.activity.BottomNavgationActivity
import kotlinx.android.synthetic.main.fragment_5.*

class MyBottomNavActivity:BottomNavgationActivity() {
    override fun getMenuId() = R.menu.bottom_nav

    override fun getFragment() = listOf(Fragment1(),Fragment2(),Fragment3(),Fragment4(),Fragment5())

    class Fragment1 : Fragment(){
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_1,container)
        }
    }
    class Fragment2 : Fragment(){
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_2,container)
        }
    }
    class Fragment3 : Fragment(){
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_3,container)
        }
    }
    class Fragment4 : Fragment(){
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_4,container)
        }
    }
    class Fragment5 : Fragment(){
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            return inflater.inflate(R.layout.fragment_5,container)
        }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            btn.setOnClickListener {
                tv_5.text = "sssss"
            }
        }
    }
}
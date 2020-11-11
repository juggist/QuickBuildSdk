package com.juggist.baselibrary

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tbruyelle.rxpermissions3.RxPermissions
import kotlinx.android.synthetic.main.activity_main.*

fun String.testMethod(value : String){

}
class MainActivity : AppCompatActivity() {
    var mHandler: Handler = object : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv.text = BuildConfig.APPLICATION_ID
        val viewModel = MessageModel()
        tv.setOnClickListener {
//            viewModel.getSocket("1","juggist")
//            viewModel.testSequence()
//            startActivity(Intent(this,MyBottomNavActivity::class.java));
            DataBindViewModel().getName()
        }
        RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
            .subscribe {
                if(it){

                }else{

                }
            }
//        var a : (reciver:String,value:String) -> Unit = String::testMethod
//        "result".testMethod("haha")
//        a("result","test")
//
//        var b :String.(String) -> Unit = ::tesdMethod2
//        tesdMethod2("juggist","haha")
//        "juggis".b("ahahha")
    }

    fun tesdMethod2(revicer:String,value:String){

    }



}


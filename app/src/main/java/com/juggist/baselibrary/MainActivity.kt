package com.juggist.baselibrary

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tbruyelle.rxpermissions3.RxPermissions
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv.text = BuildConfig.APPLICATION_ID
        tv.setOnClickListener {
            startActivity(Intent(this,MyLoadingStatusActivity::class.java));
        }
        RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
            .subscribe {
                if(it){

                }else{

                }
            }
    }

}
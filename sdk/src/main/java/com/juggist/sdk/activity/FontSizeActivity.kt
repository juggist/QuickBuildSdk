package com.juggist.sdk.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity

/**
 * app字体不随系统字体大小变化
 */
open class FontSizeActivity: AppCompatActivity()  {
    @SuppressLint("NewApi")
    override fun getResources(): Resources {
        val resources = super.getResources()
        val config = resources.configuration;
        return if (config.fontScale == 1f) {
            resources
        } else {
            config.fontScale = 1f
            baseContext.createConfigurationContext(config).resources
        }
    }

    @SuppressLint("NewApi")
    override fun attachBaseContext(newBase: Context?) {
        val config: Configuration? = newBase?.resources?.configuration
        config?.fontScale = 1f
        if(config != null){
            super.attachBaseContext(newBase.createConfigurationContext(config))
        }else{
            super.attachBaseContext(newBase)
        }

    }
}
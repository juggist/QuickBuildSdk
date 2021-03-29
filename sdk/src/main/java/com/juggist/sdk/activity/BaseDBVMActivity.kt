package com.juggist.sdk.activity

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.juggist.sdk.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 * 使用 databinding & viewModel 模式绑定activity
 * 需要注入databinding & viewModel 类型
 */
abstract class BaseDBVMActivity<VM : BaseViewModel,DB : ViewDataBinding> (private val layoutId: Int) : FontSizeActivity() {
    protected val TAG = this.javaClass.name
    protected lateinit var rootChildDataBind: DB//派生类传入的databind类型，在派生类中，必须用此对象进行数据绑定(rootChildDataBind.xx = viewModel)
    protected lateinit var rootView: View
    protected lateinit var viewModel: VM //派生类的viewmodel必须逐层继承
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootChildDataBind = DataBindingUtil.setContentView(this, layoutId)
        rootChildDataBind.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(getVmClazz(this))
        rootView = rootChildDataBind.root
    }

    private fun <VM> getVmClazz(obj: Any): VM {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
    }

    /**
     * 点击屏幕空白区域 收起软键盘
     *
     * @param ev
     * @return
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (isShouldHideKeyboard(v, ev)) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(
                    v!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    // Return whether touch the view.
    private fun isShouldHideKeyboard(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val l = intArrayOf(0, 0)
            v.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + v.getHeight()
            val right = left + v.getWidth()
            return !(event.x > left && event.x < right && event.y > top && event.y < bottom)
        }
        return false
    }
}
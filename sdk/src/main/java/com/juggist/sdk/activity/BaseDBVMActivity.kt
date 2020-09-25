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
abstract class BaseDBVMActivity<VM : BaseViewModel,DB : ViewDataBinding> (private val layoutId: Int) : AppCompatActivity() {
    protected val TAG = this.javaClass.name
    protected lateinit var rootDB: DB
    protected lateinit var rootView: View
    protected lateinit var rootVM: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootDB = DataBindingUtil.setContentView(this, layoutId)
        rootDB.lifecycleOwner = this
        rootVM = ViewModelProvider(this).get(getVmClazz(this))
        rootView = rootDB.root
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
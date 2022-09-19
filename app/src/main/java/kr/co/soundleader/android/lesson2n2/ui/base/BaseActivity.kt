package kr.co.soundleader.android.lesson2n2.ui.base

import android.content.Context
import android.icu.number.Scale.none
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kr.co.soundleader.android.lesson2n2.MyApplication
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.data.MyDataStore


abstract class BaseActivity<B : ViewDataBinding, M : BaseViewModel> : AppCompatActivity() {

    val TAG = Constants.TAG
    lateinit var myDataStore: MyDataStore
    lateinit var binding: B
    abstract val viewModel: M

    @LayoutRes
    abstract fun getLayoutId(): Int


   open fun setTopMenu(){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myDataStore = MyApplication.getInstance().getDataStore()
        overridePendingTransition(R.anim.page_enter, R.anim.page_none)

        // 초기화된 layoutResId로 databinding 객체 생성
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        // live data를 사용하기 위해 해줘야함
        binding.lifecycleOwner = this@BaseActivity


    }



    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.page_none, R.anim.page_exit)
    }

    open fun btnClick(){}

    protected fun makeToast(str:String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
    fun alertBox(context: Context, msg:String) {
        val handler: Handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(inputMessage: Message) {
                val builder = AlertDialog.Builder(context)
                //builder.setTitle("Err")
                builder.setMessage(msg)
                builder.setNegativeButton("확인", null)
                builder.show()
            }
        }
        handler.obtainMessage().sendToTarget()
    }


}
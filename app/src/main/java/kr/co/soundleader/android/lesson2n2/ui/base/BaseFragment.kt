package kr.co.soundleader.android.lesson2n2.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kr.co.soundleader.android.lesson2n2.MyApplication
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.data.MyDataStore


abstract class BaseFragment<B : ViewDataBinding, M : BaseViewModel> : Fragment() {
    val TAG = Constants.TAG

    lateinit var binding: B
    abstract val viewModel: M
    lateinit var myDataStore: MyDataStore

    @LayoutRes
    abstract fun getLayoutId(): Int

    lateinit var context: BaseActivity<*, *>

    override fun onAttach(mContext: Context) {
        super.onAttach(mContext)

        context = mContext as BaseActivity<*, *>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        myDataStore = MyApplication.getInstance().getDataStore()
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

        // live data를 사용하기 위해 해줘야함
        binding.lifecycleOwner = this@BaseFragment

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}
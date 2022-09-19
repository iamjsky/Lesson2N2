package kr.co.soundleader.android.lesson2n2.ui.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.databinding.ActivitySel1Binding
import kr.co.soundleader.android.lesson2n2.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestActivity : BaseActivity<ActivitySel1Binding, Sel1ViewModel>() {
    override val viewModel: Sel1ViewModel by viewModel()

    override fun getLayoutId(): Int {
        return R.layout.activity_sel1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

    }
}
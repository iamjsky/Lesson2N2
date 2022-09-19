package kr.co.soundleader.android.lesson2n2.ui.sign.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.ACTIVITY_SIGN_IN_TITLE
import kr.co.soundleader.android.lesson2n2.databinding.ActivitySignInBinding
import kr.co.soundleader.android.lesson2n2.ui.base.BaseActivity
import kr.co.soundleader.android.lesson2n2.ui.sign.dialog.FindPasswordDialog
import kr.co.soundleader.android.lesson2n2.ui.sign.viewmodel.SignInViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : BaseActivity<ActivitySignInBinding, SignInViewModel>() {
    override val viewModel: SignInViewModel by viewModel()


    override fun getLayoutId(): Int {
        return R.layout.activity_sign_in
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel


        setTopMenu()

        binding.btnFindPw.setOnClickListener {
            val findPasswordDialog = FindPasswordDialog()
            findPasswordDialog.show(supportFragmentManager, "FindPasswordDialog")
        }







    }

    override fun setTopMenu(){
        binding.navTopMenu.tvTopMenuTitle.text = ACTIVITY_SIGN_IN_TITLE
        binding.navTopMenu.ibtnBack.visibility = View.VISIBLE
        binding.navTopMenu.ibtnBack.setOnClickListener {
            onBackPressed()


        }
    }

}
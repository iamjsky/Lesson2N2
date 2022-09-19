package kr.co.soundleader.android.lesson2n2.ui.main.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.soundleader.android.lesson2n2.MyApplication
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.di.repository.ApiRepository
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.di.repository.UserRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseActivity
import kr.co.soundleader.android.lesson2n2.ui.base.BaseViewModel
import kr.co.soundleader.android.lesson2n2.ui.main.activity.MainActivity
import kr.co.soundleader.android.lesson2n2.ui.sign.activity.SignInActivity
import kr.co.soundleader.android.lesson2n2.ui.sign.dialog.LogoutDialog

class MainMyPageViewModel(private val apiRepo: ApiRepository, private val userRepo: UserRepository): BaseViewModel() {





    fun btnGoLoginClicked(view:View){
        val intent = Intent(view.context, SignInActivity::class.java)
        view.context.startActivity(intent)


   }

    fun btnUserLogoutClicked(view:View){
        val logoutDialog = LogoutDialog()
        logoutDialog.show((view.context as MainActivity).supportFragmentManager, "LogoutDialog")



    }
}
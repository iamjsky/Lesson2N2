package kr.co.soundleader.android.lesson2n2.ui.sign.viewmodel

import android.util.Log
import android.view.View
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_SIGN_UP_TEACHER_02
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.TAG
import kr.co.soundleader.android.lesson2n2.di.repository.ApiRepository
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseViewModel
import kr.co.soundleader.android.lesson2n2.ui.sign.activity.SignUpActivity

class SignUpTeacherSharedViewModel(private val apiRepo: ApiRepository, private val dataRepo: DataRepository): BaseViewModel() {

    fun btnNextClicked(view:View){
        dataRepo.signUpFragmentPageTag = FRAGMENT_SIGN_UP_TEACHER_02
        Log.d(TAG, "dataRepo.signUpFragmentPageTag : " + dataRepo.signUpFragmentPageTag)
        (view.context as SignUpActivity).setFragmentPage(FRAGMENT_SIGN_UP_TEACHER_02)
       // val intent = Intent(view.context, SignUpTeacherActivity::class.java)
        //view.context.startActivity(intent)
    }
}
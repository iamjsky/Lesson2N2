package kr.co.soundleader.android.lesson2n2.ui.mypage.viewmodel

import android.view.View
import kr.co.soundleader.android.lesson2n2.di.repository.ApiRepository
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseViewModel
import kr.co.soundleader.android.lesson2n2.ui.mypage.activity.MyPageModifyActivity

class MyPageModifyStudentSharedViewModel(private val apiRepo: ApiRepository, private val dataRepo: DataRepository): BaseViewModel() {


    fun btnModifyConfirmClicked(view: View){

        (view.context as MyPageModifyActivity).finish()

    }


}
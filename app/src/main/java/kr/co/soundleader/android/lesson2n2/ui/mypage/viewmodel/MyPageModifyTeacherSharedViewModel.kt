package kr.co.soundleader.android.lesson2n2.ui.mypage.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_MY_PAGE_MODIFY_TEACHER_02
import kr.co.soundleader.android.lesson2n2.di.repository.ApiRepository
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseViewModel
import kr.co.soundleader.android.lesson2n2.ui.curriculum.activity.CurriculumAddActivity
import kr.co.soundleader.android.lesson2n2.ui.main.activity.MainActivity
import kr.co.soundleader.android.lesson2n2.ui.mypage.activity.MyPageModifyActivity
import kr.co.soundleader.android.lesson2n2.ui.sign.activity.SignInActivity

class MyPageModifyTeacherSharedViewModel(private val apiRepo: ApiRepository, private val dataRepo: DataRepository): BaseViewModel() {



    fun btnNextClicked(view: View){
        dataRepo.myPageModifyFragmentPageTag = FRAGMENT_MY_PAGE_MODIFY_TEACHER_02
        Log.d(Constants.TAG, "dataRepo.myPageModifyFragmentPageTag : " + dataRepo.myPageModifyFragmentPageTag)
        (view.context as MyPageModifyActivity).setFragmentPage(Constants.FRAGMENT_MY_PAGE_MODIFY_TEACHER_02)
        // val intent = Intent(view.context, SignUpTeacherActivity::class.java)
        //view.context.startActivity(intent)
    }

    fun btnModifyConfirmClicked(view: View){

        (view.context as MyPageModifyActivity).finish()

    }
}
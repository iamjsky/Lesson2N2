package kr.co.soundleader.android.lesson2n2.ui.mypage.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.di.repository.ApiRepository
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseViewModel
import kr.co.soundleader.android.lesson2n2.ui.curriculum.activity.CurriculumAddActivity
import kr.co.soundleader.android.lesson2n2.ui.mypage.activity.MyPageCurriculumModifyTeacherActivity

class MyPageCurriculumModifyTeacherSharedViewModel(private val apiRepo: ApiRepository, private val dataRepo: DataRepository): BaseViewModel() {
    var youTubeUrl = MutableLiveData<String>()


    fun btnNextClicked(view: View){
        dataRepo.myPageCurriculumModifyTeacherFragmentPageTag = Constants.FRAGMENT_MY_PAGE_CURRICULUM_MODIFY_TEACHER_02
        Log.d(Constants.TAG, "dataRepo.myPageCurriculumModifyTeacherFragmentPageTag : " + dataRepo.myPageCurriculumModifyTeacherFragmentPageTag)
        (view.context as MyPageCurriculumModifyTeacherActivity).setFragmentPage(Constants.FRAGMENT_MY_PAGE_CURRICULUM_MODIFY_TEACHER_02)

    }
}
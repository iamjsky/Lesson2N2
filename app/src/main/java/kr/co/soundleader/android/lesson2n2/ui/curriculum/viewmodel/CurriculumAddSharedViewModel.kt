package kr.co.soundleader.android.lesson2n2.ui.curriculum.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.di.repository.ApiRepository
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseViewModel
import kr.co.soundleader.android.lesson2n2.ui.curriculum.activity.CurriculumAddActivity

class CurriculumAddSharedViewModel(private val apiRepo: ApiRepository, private val dataRepo: DataRepository) : BaseViewModel() {

        var youTubeUrl = MutableLiveData<String>()


    fun btnNextClicked(view: View){
        dataRepo.curriculumAddFragmentPageTag = Constants.FRAGMENT_CURRICULUM_ADD_02
        Log.d(Constants.TAG, "dataRepo.curriculumAddFragmentPageTag : " + dataRepo.curriculumAddFragmentPageTag)
        (view.context as CurriculumAddActivity).setFragmentPage(Constants.FRAGMENT_CURRICULUM_ADD_02)

    }







}
package kr.co.soundleader.android.lesson2n2.ui.mypage.viewmodel

import androidx.lifecycle.MutableLiveData
import kr.co.soundleader.android.lesson2n2.data.model.api.ArticleList
import kr.co.soundleader.android.lesson2n2.di.repository.ApiRepository
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseViewModel

class MyPageCurriculumTeacherViewModel(private val apiRepo: ApiRepository, private val dataRepo: DataRepository): BaseViewModel() {

    var page: Int = 1

    val songListLiveData = MutableLiveData<List<ArticleList>>()

    fun getSongList(){
        apiRepo.getIF103(page, songListLiveData)

        page++

    }
}
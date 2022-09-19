package kr.co.soundleader.android.lesson2n2.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kr.co.soundleader.android.lesson2n2.data.model.api.ArticleList
import kr.co.soundleader.android.lesson2n2.di.repository.ApiRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseViewModel

class MainTeacherViewModel(private val apiRepo: ApiRepository): BaseViewModel() {

    var page: Int = 1

    val songListLiveData = MutableLiveData<List<ArticleList>>()
    // var songListData: List<ArticleList> = emptyList()


    fun getSongList(){
         apiRepo.getIF103(page, songListLiveData)

        page++

    }
}
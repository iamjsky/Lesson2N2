package kr.co.soundleader.android.lesson2n2.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.data.model.api.ArticleList
import kr.co.soundleader.android.lesson2n2.di.repository.ApiRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseViewModel

class MainClassRoomStudentSharedViewModel(private val apiRepo: ApiRepository): BaseViewModel() {
    var value:Int = 0

    fun addValue(){
        value++
        Log.d(Constants.TAG, "value L: " + value)
    }

    var page1: Int = 1
    var page2: Int = 1
    var page3: Int = 1

    val songListLiveData1 = MutableLiveData<List<ArticleList>>()
    val songListLiveData2 = MutableLiveData<List<ArticleList>>()
    val songListLiveData3 = MutableLiveData<List<ArticleList>>()
    // var songListData: List<ArticleList> = emptyList()


    fun getSongList1(){
        apiRepo.getIF103(page1, songListLiveData1)

        page1++

    }
    fun getSongList2(){
        apiRepo.getIF103(page2, songListLiveData2)

        page2++

    }
    fun getSongList3(){
        apiRepo.getIF103(page3, songListLiveData3)

        page3++

    }
}
package kr.co.soundleader.android.lesson2n2.ui.classroom.viewmodel

import androidx.lifecycle.MutableLiveData
import kr.co.soundleader.android.lesson2n2.data.model.api.ArticleList
import kr.co.soundleader.android.lesson2n2.di.repository.ApiRepository
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseViewModel

class ClassRoomTeacherViewModel(private val apiRepo: ApiRepository, private val userRepo: DataRepository) : BaseViewModel() {



    var page: Int = 1

    val songListLiveData = MutableLiveData<List<ArticleList>>()

    fun getSongList(){
        if(page < 10){
            apiRepo.getIF103(page, songListLiveData)

            page++
        }


    }








}
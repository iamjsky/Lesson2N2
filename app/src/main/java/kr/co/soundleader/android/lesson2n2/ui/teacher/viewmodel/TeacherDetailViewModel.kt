package kr.co.soundleader.android.lesson2n2.ui.teacher.viewmodel


import androidx.lifecycle.MutableLiveData
import kr.co.soundleader.android.lesson2n2.ui.base.BaseViewModel
import kr.co.soundleader.android.lesson2n2.data.model.api.ArticleList
import kr.co.soundleader.android.lesson2n2.di.repository.ApiRepository
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository


class TeacherDetailViewModel(private val apiRepo: ApiRepository, private val userRepo: DataRepository) : BaseViewModel() {

    val songListLiveData = MutableLiveData<List<ArticleList>>()
    var page: Int = 1

    fun getSongList(){
        apiRepo.getIF103(page, songListLiveData)

        page++

    }










}
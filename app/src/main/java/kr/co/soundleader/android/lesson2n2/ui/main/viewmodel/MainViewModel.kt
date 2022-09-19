package kr.co.soundleader.android.lesson2n2.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import kr.co.soundleader.android.lesson2n2.di.repository.ApiRepository
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.di.repository.UserRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseViewModel

class MainViewModel(private val apiRepo: ApiRepository, private val userRepo: UserRepository): BaseViewModel() {
    var userLoginState = MutableLiveData<Boolean>()



}
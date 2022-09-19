package kr.co.soundleader.android.lesson2n2.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.soundleader.android.lesson2n2.MyApplication
import kr.co.soundleader.android.lesson2n2.data.MyDataStore
import kr.co.soundleader.android.lesson2n2.data.model.api.ArticleList

open class BaseViewModel : ViewModel() {
    var myDataStore: MyDataStore = MyApplication.getInstance().getDataStore()
    val isLogin = MutableLiveData<Boolean>()
    fun setLoginState(value: Boolean) {
        isLogin.postValue(value)

    }
}
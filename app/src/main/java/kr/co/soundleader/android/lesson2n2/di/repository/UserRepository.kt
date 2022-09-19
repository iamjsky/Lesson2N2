package kr.co.soundleader.android.lesson2n2.di.repository

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kr.co.soundleader.android.lesson2n2.api.ApiService
import kr.co.soundleader.android.lesson2n2.data.MyDataStore
import kr.co.soundleader.android.lesson2n2.data.model.user.UserInfo
import kr.co.soundleader.android.lesson2n2.data.model.user.UserType.Companion.GUEST

class UserRepository(private val apiService: ApiService) {
    var userLoginType: Int = GUEST
    var userName: String = ""
    var userEmail: String = ""

    init {
        userLoginType = GUEST
        userName = ""
        userEmail = ""
    }
    fun setUserData(userInfo: UserInfo){
        userLoginType = userInfo.userLoginType!!
        userName = userInfo.userName.toString()
        userEmail = userInfo.userEmail.toString()

    }

    fun setUserLogout(){
        userLoginType = GUEST
        userName = ""
        userEmail = ""
    }



}
package kr.co.soundleader.android.lesson2n2.data.model.user

import kr.co.soundleader.android.lesson2n2.data.model.user.UserType.Companion.GUEST

class UserInfo {
    var userLoginType: Int? = GUEST
    var userName: String? = null
    var userEmail: String? = null
}
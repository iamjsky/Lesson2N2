package kr.co.soundleader.android.lesson2n2.data.model.user

interface UserType {
    companion object {
        const val GUEST: Int = 0
        const val TEACHER: Int = 1
        const val STUDENT: Int = 2
    }
}
package kr.co.soundleader.android.lesson2n2.api

interface ApiCallback {

    fun <T> result(value: Boolean, msg: String, data: T)

}
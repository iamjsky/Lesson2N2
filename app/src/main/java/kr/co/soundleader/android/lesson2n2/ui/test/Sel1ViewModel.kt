package kr.co.soundleader.android.lesson2n2.ui.test

import android.content.Intent
import android.view.View
import kr.co.soundleader.android.lesson2n2.di.repository.ApiRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseViewModel

class Sel1ViewModel(private val apiRepo: ApiRepository): BaseViewModel() {

    fun masterTypeOnClick(view: View){
        val intent = Intent(view.context, Sel2Activity::class.java)
        intent.putExtra("LessonType", "master")
        view.context.startActivity(intent)
    }
    fun viewerTypeOnClick(view: View){
        val intent = Intent(view.context, Sel2Activity::class.java)
        intent.putExtra("LessonType", "viewer")
        view.context.startActivity(intent)
    }

}
package kr.co.soundleader.android.lesson2n2.ui.test

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import androidx.lifecycle.MutableLiveData
import kr.co.soundleader.android.lesson2n2.api.ApiCallback
import kr.co.soundleader.android.lesson2n2.data.model.webrtc.RoomInfo
import kr.co.soundleader.android.lesson2n2.di.repository.ApiRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseViewModel
import kr.co.soundleader.android.lesson2n2.ui.rvtest.RecyclerViewActivity
import kr.co.soundleader.android.lesson2n2.utils.StringUtil

class Sel2ViewModel(private val apiRepo: ApiRepository): BaseViewModel() {

    var alertBoxState = MutableLiveData<Boolean>()
    var alertBoxMsg = MutableLiveData<String>()




    fun roomJoinChecker(context: Context,
                        lessonType: String, cameraType: String, classCode: String, classPw: String, nickName: String){
        apiRepo.getIF001(lessonType, cameraType, classCode, classPw, nickName,
            object : ApiCallback {
                override fun <T> result(value: Boolean, msg: String, data: T) {
                    alertBoxState.postValue(value)
                    alertBoxMsg.postValue(msg)
                    if(value){
                        nextIntent(context, data as RoomInfo)
                    }
                }
            })
    }

    fun nextIntent(context: Context, roomInfo: RoomInfo) {
        val handler: Handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(inputMessage: Message) {
                val intent = Intent(context, RecyclerViewActivity::class.java)
                intent.putExtra("roomInfo", roomInfo)
                context.startActivity(intent)
            }
        }
        handler.obtainMessage().sendToTarget()
    }

}
package kr.co.soundleader.android.lesson2n2.api.model

import com.google.gson.annotations.SerializedName

import kr.co.soundleader.android.lesson2n2.data.model.webrtc.RoomInfo

data class IF001(


    @SerializedName("header") var header: Header? = Header(),
    @SerializedName("room_info") var roomInfo: RoomInfo


)


data class Header(

    @SerializedName("code") var code: Int? = null,
    @SerializedName("message") var message: String? = null

)

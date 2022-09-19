package kr.co.soundleader.android.lesson2n2.data.model.webrtc

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RoomInfo(
    @SerializedName("lessonType") var lessonType: String? = null,
    @SerializedName("cameraType") var cameraType: String? = null,
    @SerializedName("classCode") var classCode: String? = null,
    @SerializedName("classPw") var classPw: String? = null,
    @SerializedName("nickName") var nickName: String? = null,
    @SerializedName("room_idx") var room_idx: String? = null,
    @SerializedName("room_id") var room_id: String? = null,
    @SerializedName("user_idx") var user_idx: String? = null,
    @SerializedName("user_id") var user_id: String? = null,
    @SerializedName("pdf_file") var pdf_file: String? = null,
    @SerializedName("pdf_realName") var pdf_realName: String? = null
) : Parcelable


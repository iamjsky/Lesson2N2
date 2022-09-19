package kr.co.soundleader.android.lesson2n2.api

import kr.co.soundleader.android.lesson2n2.api.model.IF001
import kr.co.soundleader.android.lesson2n2.data.model.api.IF103
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    // test member token : pZFiUDyieYsUHmUixB_hjwBs96tzK0v6!7pQKX5ZRyPvU

    //test
        @FormUrlEncoded
        @POST("IF103.php")
        fun getIF103(@Field("token") token: String,
                     @Field("genre") genre: Int,
                     @Field("page") page: Int
    ): Call<IF103>



    @FormUrlEncoded
    @POST("IF001.php")
    fun getIF001(
                    @Field("LessonType") room_master: String,
                    @Field("CameraType") room_type: String,
                    @Field("classCode") room_channel: String,
                    @Field("classPw") room_pw: String,
                    @Field("nickName") room_nicname: String
    ): Call<IF001>
}
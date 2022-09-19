package kr.co.soundleader.android.lesson2n2.di.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kr.co.soundleader.android.lesson2n2.api.ApiCallback
import kr.co.soundleader.android.lesson2n2.api.ApiService
import kr.co.soundleader.android.lesson2n2.api.model.IF001
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.TAG
import kr.co.soundleader.android.lesson2n2.data.model.api.ArticleList
import kr.co.soundleader.android.lesson2n2.data.model.api.IF103
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRepository(private val apiService: ApiService) {


    //TEST
      fun getIF103(page: Int, aaa: MutableLiveData<List<ArticleList>>) {

    //TEST
        apiService.getIF103("pZFiUDyieYsUHmUixB_hjwBs96tzK0v6!7pQKX5ZRyPvU", 0, page).enqueue(object : Callback<IF103>{
            override fun onResponse(call: Call<IF103>, response: Response<IF103>) {

                    Log.d("SL_DEBUG", "IF103 isSuccessful" )
                if(response.isSuccessful) {
                    response.body()?.let {

                        aaa.postValue(it.articleList)
                    }
                }




            }

            override fun onFailure(call: Call<IF103>, t: Throwable) {
                Log.d("SL_DEBUG", "IF103 onFailure : " + t.stackTrace )
            }
        })


       }

    fun getIF001(
        lessonType: String, CameraType: String, classCode: String, classPw: String, nickName: String,
        callback: ApiCallback
    ) {

        apiService.getIF001(
            lessonType, CameraType, classCode, classPw, nickName
        ).enqueue(object : Callback<IF001> {
            override fun onResponse(call: Call<IF001>, response: Response<IF001>) {

                Log.d(TAG, "IF001 isSuccessful")
                if (response.isSuccessful) {
                    response.body()?.let {

                        val data: IF001 = response.body()!!
                        val code: Int = data.header?.code!!
                        val msg: String = data.header?.message+""

                        if(code == 200){

                            callback.result(true, msg, data.roomInfo)

                        }else{
                            callback.result(false, msg, null)
                        }

                    }
                }


            }

            override fun onFailure(call: Call<IF001>, t: Throwable) {
                Log.d("SL_DEBUG", "IF001 onFailure : " + t.stackTrace)
                callback.result(false, t.toString(), null)
            }
        })

    }

}
package kr.co.soundleader.android.lesson2n2.data.model.api

import com.google.gson.annotations.SerializedName

data class Header (

    @SerializedName("code"    ) var code    : Int?    = null,
    @SerializedName("message" ) var message : String? = null

)

data class IF103 (

    @SerializedName("header"       ) var header      : Header?                = Header(),
    @SerializedName("total_record" ) var totalRecord : String?                = null,
    @SerializedName("total_page"   ) var totalPage   : Int?                   = null,
    @SerializedName("article_list" ) var articleList : ArrayList<ArticleList> = arrayListOf()



)

data class ArticleList (

    @SerializedName("idx"        ) var idx       : String? = null,
    @SerializedName("title"      ) var title     : String? = null,
    @SerializedName("genre_name" ) var genreName : String? = null,
    @SerializedName("artist"     ) var artist    : String? = null,
    @SerializedName("cover_img"  ) var coverImg  : String? = null,
    @SerializedName("isCheck"  ) var isCheck  : Boolean? = false

)
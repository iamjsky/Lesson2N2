package kr.co.soundleader.android.lesson2n2.utils

import java.util.regex.Pattern

object YoutubeUtil {

    // 유튜브 섬네일 가져옴
    fun getThumbnail(url: String): String {
        val vId = getVideoId(url)
        return "https://img.youtube.com/vi/$vId/hqdefault.jpg"
    }

    fun getVideoId(url: String?): String {
        var vId = ""
        val pattern = Pattern.compile(
            "^.*(?:(?:youtu\\.be\\/|v\\/|vi\\/|u\\/\\w\\/|embed\\/)|(?:(?:watch)?\\?v(?:i)?=|\\&v(?:i)?=))([^#\\&\\?]*).*",
            Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(url ?: "")
        if (matcher.matches()) {
            vId = matcher.group(1) ?: ""
        }
        return vId
    }
    // 유튜브 주소인지 판별
    fun isYoutube(url: String?): Boolean {
        return getVideoId(url).isNotEmpty()
    }
}
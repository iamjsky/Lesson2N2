package kr.co.soundleader.android.lesson2n2.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class StringUtil {

    companion object{


        fun getTodayString(): String {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("MMddHH")
            //val formatter = DateTimeFormatter.ofPattern("yyMMdd")
            //val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
            //val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            return current.format(formatter)
        }

        fun getRandomString(length: Int) : String {
            val list = mutableListOf<Int>().apply {
                for (i in 1..9) this.add(i)
            }
            list.shuffle()
            list.subList(0,length)
            return list.joinToString("")
        }

        //캐시가 남는지 앱시작 마다 처음에 나온 번호가 계속 나옴.
        fun getRandomString2(length: Int) : String {
            val charset = "0123456789"
            return (1..length)
                .map {charset.random()}
                .joinToString("")
        }

        fun isValidClassCode(cCode: String?): Boolean {
            val trimmedCode = cCode?.trim().toString()
            val exp = Regex("^[0-9]{6,}\$")
            return !trimmedCode.isNullOrEmpty() && exp.matches(trimmedCode)
        }

        // 숫자/문자/특수문자 중 2가지 포함(6~15자리) :  ^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#$%^&*])(?=.*[0-9!@#$%^&*]).{6,15}$
        // 숫자/문자/특수문자 포함(8~15자리) : ^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,15}.$
        fun isValidPassword(cCode: String?): Boolean {
            val trimmedPw = cCode?.trim().toString()
            val exp = Regex("^[a-zA-Z0-9]{2,}")
            return !trimmedPw.isNullOrEmpty() && exp.matches(trimmedPw)
        }

        // 한글/영문/숫자 허용
        fun isValidNickname(nickname: String?): Boolean {
            val trimmedNickname = nickname?.trim().toString()
            val exp = Regex("^[가-힣ㄱ-ㅎa-zA-Z0-9._-]{2,}\$")
            return !trimmedNickname.isNullOrEmpty() && exp.matches(trimmedNickname)
        }


    }
}
package kr.co.soundleader.android.lesson2n2.di.repository

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kr.co.soundleader.android.lesson2n2.api.ApiService
import kr.co.soundleader.android.lesson2n2.data.MyDataStore

class DataRepository(private val apiService: ApiService) {
    lateinit var curriculumTeacherDetailData: String

    lateinit var signUpFragmentPageTag: String

    lateinit var curriculumAddFragmentPageTag: String

    lateinit var presentFragmentPageTag: String

    lateinit var myPageModifyFragmentPageTag: String

    lateinit var myPageCurriculumModifyTeacherFragmentPageTag: String



}
package kr.co.soundleader.android.lesson2n2.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.*
import java.io.IOException

class MyDataStore(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences>  by preferencesDataStore(name = "dataStore")

    private val LOGIN_STATE = booleanPreferencesKey("LOGIN_STATE")
   // private val AUTO_LOGIN_STATE = booleanPreferencesKey("AUTO_LOGIN_STATE")
   // private val USER_LOGIN_TYPE = intPreferencesKey("USER_LOGIN_TYPE")

    // 유저 로그인 상태
    val loginState : Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {preferences ->
            preferences[LOGIN_STATE] ?: false
        }

    suspend fun setLoginState(value : Boolean){
        context.dataStore.edit { preferences ->
            preferences[LOGIN_STATE] = value
        }

    }
    //

//    // 자동 로그인 상태
//    val autoLoginState : Flow<Boolean> = context.dataStore.data
//        .catch { exception ->
//            if (exception is IOException) {
//                emit(emptyPreferences())
//            } else {
//                throw exception
//            }
//        }
//        .map {preferences ->
//            preferences[AUTO_LOGIN_STATE] ?: false
//        }
//
//    suspend fun setAutoLoginState(value : Boolean){
//        context.dataStore.edit { preferences ->
//            preferences[AUTO_LOGIN_STATE] = value
//        }
//
//    }
    //

//    // 유저 타입
//    val userLoginType : Flow<Int> = context.dataStore.data
//        .catch { exception ->
//            if (exception is IOException) {
//                emit(emptyPreferences())
//            } else {
//                throw exception
//            }
//        }
//        .map {preferences ->
//            preferences[USER_LOGIN_TYPE] ?: 0
//        }
//
//    suspend fun setUserLoginType(value : Int){
//        context.dataStore.edit { preferences ->
//            preferences[USER_LOGIN_TYPE] = value
//        }
//
//    }
//    //


}
package kr.co.soundleader.android.lesson2n2.ui.sign.viewmodel

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kr.co.soundleader.android.lesson2n2.MyApplication
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_SIGN_UP_STUDENT_01
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_SIGN_UP_TEACHER_01
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.TAG
import kr.co.soundleader.android.lesson2n2.data.model.user.UserInfo
import kr.co.soundleader.android.lesson2n2.data.model.user.UserType.Companion.STUDENT
import kr.co.soundleader.android.lesson2n2.data.model.user.UserType.Companion.TEACHER
import kr.co.soundleader.android.lesson2n2.di.repository.ApiRepository
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.di.repository.UserRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseViewModel
import kr.co.soundleader.android.lesson2n2.ui.sign.activity.SignInActivity
import kr.co.soundleader.android.lesson2n2.ui.sign.activity.SignUpActivity


class SignInViewModel(private val apiRepo: ApiRepository, private val dataRepo: DataRepository, private val userRepo: UserRepository): BaseViewModel() {
    var testText = MutableLiveData<String>()


    var loginType = MutableLiveData<Int>()
    var userEmailId = MutableLiveData<String>()
    var userPw = MutableLiveData<String>()

    init {
        loginType.postValue(TEACHER)
        userEmailId.postValue("")
        userPw.postValue("")
    }


    fun setUserLoginType(radioGroup: RadioGroup?, id: Int){
        when(id){
            R.id.rb_type_teacher -> {
                loginType.postValue(TEACHER)
            }
            R.id.rb_type_student -> {
                loginType.postValue(STUDENT)
            }
        }
    }



    fun btnLoginClicked(view: View){

        //로그인 시도
        val loginUserEmailId = userEmailId.value
        val loginUserPw = userPw.value

        //api 통신



        //로그인 성공시, 나중에 콜백 데이터로 변경
        val userInfo = UserInfo()
        when(loginType.value){

            TEACHER -> {
                userInfo.userName = "강사 테스터"
                userInfo.userEmail = "soundleaders_teacher@gmail.com"
                userInfo.userLoginType = loginType.value!!
            }
            STUDENT -> {
                userInfo.userName = "학생 테스터"
                userInfo.userEmail = "soundleaders_student@gmail.com"
                userInfo.userLoginType = loginType.value!!
            }


        }

        userRepo.setUserData(userInfo)

        CoroutineScope(Dispatchers.Main).launch {
            myDataStore.setLoginState(true)
            Log.d(TAG, "btnLoginClicked :: userRepo -> " + userRepo.userName)






            (view.context as SignInActivity).finish()


        }

        //실패시
       // CoroutineScope(Dispatchers.Main).launch {
            //userRepo.setUserLogout()
        //  myDataStore.setLoginState(false)

      //  }




    }



    fun btnTeacherSignUpClicked(view:View){
        dataRepo.signUpFragmentPageTag = FRAGMENT_SIGN_UP_TEACHER_01

        val intent = Intent(view.context, SignUpActivity::class.java)

        Log.d(TAG, "dataRepo.signUpFragmentPageTag : " + dataRepo.signUpFragmentPageTag)
        view.context.startActivity(intent)

    }

    fun btnStudentSignUpClicked(view:View){
        dataRepo.signUpFragmentPageTag = FRAGMENT_SIGN_UP_STUDENT_01
        val intent = Intent(view.context, SignUpActivity::class.java)


        Log.d(TAG, "dataRepo.signUpFragmentPageTag : " + dataRepo.signUpFragmentPageTag)
        view.context.startActivity(intent)
    }






}
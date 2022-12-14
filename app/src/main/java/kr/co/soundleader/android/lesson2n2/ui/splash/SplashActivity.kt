package kr.co.soundleader.android.lesson2n2.ui.splash

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kr.co.soundleader.android.lesson2n2.MyApplication
import kr.co.soundleader.android.lesson2n2.ui.base.BaseActivity
import kr.co.soundleader.android.lesson2n2.ui.splash.SplashViewModel
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.REMON_PERMISSION_REQUEST
import kr.co.soundleader.android.lesson2n2.data.MyDataStore
import kr.co.soundleader.android.lesson2n2.data.model.user.UserInfo
import kr.co.soundleader.android.lesson2n2.data.model.user.UserType
import kr.co.soundleader.android.lesson2n2.data.model.user.UserType.Companion.GUEST
import kr.co.soundleader.android.lesson2n2.data.model.user.UserType.Companion.STUDENT
import kr.co.soundleader.android.lesson2n2.data.model.user.UserType.Companion.TEACHER

import kr.co.soundleader.android.lesson2n2.databinding.ActivitySplashBinding
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.di.repository.UserRepository
import kr.co.soundleader.android.lesson2n2.ui.main.activity.MainActivity
import kr.co.soundleader.android.lesson2n2.ui.rvtest.RecyclerViewActivity
import kr.co.soundleader.android.lesson2n2.ui.test.Sel1Activity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override val viewModel: SplashViewModel by viewModel()
    val userRepo: UserRepository by inject()

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel


    }


    override fun onResume() {
        super.onResume()

        checkPermission()


    }

    // ??????????????? ????????? ????????? ?????? ???????????? ????????????, ????????? ?????? ????????? ??????????????? ?????? ??????????????????.
    // ??????????????? ????????? ????????? ??????????????? ??????  ????????? CAMERA,RECORD_AUDIO,WRITE_EXTERNAL_STORAGE ?????????.
    private fun checkPermission() {

        val mandatoryPermissions = arrayListOf(
            "android.permission.CAMERA",
            "android.permission.RECORD_AUDIO",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.CHANGE_NETWORK_STATE",
            "android.permission.MODIFY_AUDIO_SETTINGS",
            "android.permission.INTERNET",
            "android.permission.ACCESS_NETWORK_STATE",
            "android.permission.READ_PHONE_STATE"
        )

        val grantList = mutableListOf<String>()
        for (permission in mandatoryPermissions) {
            val permissionResult = ContextCompat.checkSelfPermission(this, permission)
            //Log.d(TAG, " ==> permissionResult => $permission($permissionResult)")
            if (permissionResult != PackageManager.PERMISSION_GRANTED) {
                grantList.add(permission)
                Log.d(TAG, " ==> ?????????????????? => $permission")
            }
        }

        if (grantList.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                grantList.toTypedArray(),
                REMON_PERMISSION_REQUEST
            )
        } else {
            nextAct()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REMON_PERMISSION_REQUEST) {
            val deniedList = arrayListOf<String>()
            if (grantResults.isNotEmpty()) {
                for (i in grantResults.indices) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        deniedList.add(permissions[i])
                        Log.d(TAG, " ==> PERMISSION_DENIED => $i ==> ${permissions[i]}");
                    }
                }
            }

            if (deniedList.isNotEmpty()) {
                // ?????? ????????? ?????? ??????
                Snackbar.make(binding.root, "????????? ???????????????.", Snackbar.LENGTH_SHORT).show()
            } else {
                nextAct()
            }
        }
    }

    private fun nextAct() {

        CoroutineScope(Dispatchers.Main).launch {

            val loginState = myDataStore.loginState.first()
            Log.d(TAG, "SPLASH :: loginState -> " + loginState)
            if(loginState){
                // ????????? ???????????? ???????????? ?????????

                // ?????????


                //????????? ?????????, ????????? ?????? ???????????? ??????
                val userInfo = UserInfo()
                when(TEACHER){

                    TEACHER -> {
                        userInfo.userName = "?????? ????????? ?????? ?????????"
                        userInfo.userEmail = "splash_teacher@gmail.com"
                        userInfo.userLoginType = TEACHER
                    }
                    STUDENT -> {
                        userInfo.userName = "?????? ????????? ?????? ?????????"
                        userInfo.userEmail = "splash_student@gmail.com"
                        userInfo.userLoginType = STUDENT
                    }


                }

                userRepo.setUserData(userInfo)

                // ?????????
               // userRepo.setUserLogout()



            }else{
                userRepo.setUserLogout()
            }

            moveMainActivity()
        }




    }


    fun moveMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}
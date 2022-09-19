package kr.co.soundleader.android.lesson2n2.ui.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.databinding.ActivitySel2Binding
import kr.co.soundleader.android.lesson2n2.ui.base.BaseActivity
import kr.co.soundleader.android.lesson2n2.utils.NetworkManager
import kr.co.soundleader.android.lesson2n2.utils.StringUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class Sel2Activity : BaseActivity<ActivitySel2Binding, Sel2ViewModel>() {
    override val viewModel: Sel2ViewModel by viewModel()

    override fun getLayoutId(): Int {
        return R.layout.activity_sel2
    }

    private lateinit var lessonType: String
    private lateinit var cameraType: String
    private val todayString: String = StringUtil.getTodayString()

    override fun onRestart() {
        super.onRestart()
        if (lessonType == "master" && cameraType == "Face") {
            //val classCode: String = Utils.getRandomString(6)
            val classCode: String = todayString
            binding.editTxt1.setText(classCode)
        }
        Log.d(TAG, " ==> onRestart ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        setLessonType()
        setObserver()
        setBtnClickListener()


    }

    fun setLessonType() {

        lessonType = this.intent.getStringExtra("LessonType") ?: "master"
        Log.d(TAG, " ==> onCreate : LessonType => $lessonType");

        when (lessonType) {
            "master" -> {
                binding.imageView1.setImageResource(R.drawable.mastersel)
                binding.textView2.text = "클래스 생성"

            }
            "viewer" -> {
                binding.imageView1.setImageResource(R.drawable.usersel)
                binding.textView2.text = "참가"

            }
        }

    }

    fun setObserver() {

        viewModel.alertBoxState.observe(this, Observer {
            if(!it){
                alertBox(this, viewModel.alertBoxMsg.value+"")

            }
        })


    }



    fun setBtnClickListener() {
        binding.imageView21.setOnClickListener {
            binding.relativeLayout1.visibility = View.VISIBLE
            binding.relativeLayout2.visibility = View.INVISIBLE
            if (lessonType == "master") {
                binding.editTxt1.isEnabled = false
                //val classCode: String = Utils.getRandomString(6)
                val classCode: String = todayString
                binding.editTxt1.setText(classCode)
                binding.button.text = "클래스 생성"
            } else {
                binding.editTxt1.isEnabled = true
                binding.editTxt1.setText(todayString)
                binding.button.text = "클래스 참가"
            }
            binding.editTxt3.hint = "이름/닉네임"
            cameraType = "Face"
        }

        binding.imageView22.setOnClickListener {
            binding.relativeLayout1.visibility = View.VISIBLE
            binding.relativeLayout2.visibility = View.INVISIBLE
            binding.editTxt1.isEnabled = true
            binding.editTxt1.setText(todayString)
            when (lessonType) {
                "master" -> {
                    binding.editTxt3.hint = "이름/닉네임(생성 닉네임과 같은이름으로 입력)"
                }
                "viewer" -> {
                    binding.editTxt3.hint = "이름/닉네임(참가 닉네임과 같은이름으로 입력)"
                }
            }
            binding.button.text = "건반카메라 참가"
            cameraType = "Piano"
        }

        binding.button.setOnClickListener {
            if (validChecker()) {
                // 네트워크가 연결되어 있지 않은 경우
                if (!NetworkManager.checkNetworkState(this)) {
                    alertBox(this, "인터넷이 연결되어 있지 않습니다.")
                } else {   // 네트워크가 연결되어 있는 경우
                    val classCode: String = binding.editTxt1.text.trim().toString()
                    val classPw: String = binding.editTxt2.text.trim().toString()
                    val nickName: String = binding.editTxt3.text.trim().toString()

                    viewModel.roomJoinChecker(this, lessonType, cameraType, classCode, classPw, nickName)
                }
            }
        }

        binding.button2.setOnClickListener {
            binding.relativeLayout1.visibility = View.INVISIBLE
            binding.relativeLayout2.visibility = View.VISIBLE

            //binding.editTxt1.setText("")
            //binding.editTxt2.setText("")
            //binding.editTxt3.setText("")
        }
    }


    private fun validChecker(): Boolean {

        var isCheck: Boolean = true
        val classCode: String = binding.editTxt1.text.trim().toString()
        val classPw: String = binding.editTxt2.text.trim().toString()
        val nickName: String = binding.editTxt3.text.trim().toString()

        if (classCode.isEmpty()) {
            binding.errTxt1.text = "** 클래스코드 6자를 입력해 주세요."
            isCheck = false
        } else {
            if (!StringUtil.isValidClassCode(classCode)) {
                binding.errTxt1.text = "** 클래스코드는 숫자만 6자리 입력해 주세요."
                isCheck = false
            } else {
                binding.errTxt1.text = ""
                isCheck = true
            }
        }

        if (isCheck) {
            if (classPw.isEmpty()) {
                binding.errTxt2.text = "** 비밀번호를 입력해 주세요."
                isCheck = false
            } else {
                if (!StringUtil.isValidPassword(classPw)) {
                    binding.errTxt2.text = "** 비밀번호를 공백없이 2자 이상 입력해 주세요.(영문/숫자)"
                    isCheck = false
                } else {
                    binding.errTxt2.text = ""
                    isCheck = true
                }
            }
        }

        if (isCheck) {
            if (nickName.isEmpty()) {
                binding.errTxt3.text = "** 닉네임을 공백없이 2자 이상 입력해 주세요."
                isCheck = false
            } else {
                if (!StringUtil.isValidNickname(nickName)) {
                    binding.errTxt3.text = "** 닉네임을 숫자,영어,한국어를 공백없이 2자 이상 입력해 주세요."
                    isCheck = false
                } else {
                    binding.errTxt3.text = ""
                    isCheck = true
                }
            }
        }

        return isCheck
    }

}
package kr.co.soundleader.android.lesson2n2.ui.sign.dialog

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.soundleader.android.lesson2n2.MyApplication
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.databinding.DialogLogoutBinding
import kr.co.soundleader.android.lesson2n2.databinding.DialogNeedLoginBinding
import kr.co.soundleader.android.lesson2n2.di.repository.UserRepository
import kr.co.soundleader.android.lesson2n2.ui.main.activity.MainActivity
import kr.co.soundleader.android.lesson2n2.ui.sign.activity.SignInActivity
import kr.co.soundleader.android.lesson2n2.ui.sign.viewmodel.SignDialogSharedViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LogoutDialog : DialogFragment() {
    val userRepo : UserRepository by inject()

    companion object {
        fun newInstance() = LogoutDialog()
    }

    private val _viewModel: SignDialogSharedViewModel by sharedViewModel()
    val size = Point()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DialogLogoutBinding.inflate(inflater, container, false)
            .apply {
                this.lifecycleOwner = this@LogoutDialog
                this.viewModel = _viewModel
            }
        // 뒤로가기 버튼, 빈 화면 터치를 통해 dialog가 사라지지 않도록
        setCancelable(false)


        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay

        display.getSize(size)

        //로그아웃 처리
        binding.btnOk.setOnClickListener {

            userRepo.setUserLogout()
            CoroutineScope(Dispatchers.Main).launch {
                MyApplication.getInstance().getDataStore().setLoginState(false)
                Log.d(Constants.TAG, "LogoutDialog :: userRepo -> " + userRepo.userName)
                (context as MainActivity).viewModel.userLoginState.postValue(false)

                dismiss()
            }

        }

        binding.btnCancel.setOnClickListener {
            dismiss()

        }





        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        params?.width = (deviceWidth * 0.8).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }
}
package kr.co.soundleader.android.lesson2n2.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kr.co.soundleader.android.lesson2n2.MyApplication
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_MY_PAGE_MODIFY_STUDENT_01
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_MY_PAGE_MODIFY_TEACHER_01
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_PRIVACY
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_TERM_OF_USE
import kr.co.soundleader.android.lesson2n2.data.model.user.UserType.Companion.GUEST
import kr.co.soundleader.android.lesson2n2.data.model.user.UserType.Companion.STUDENT
import kr.co.soundleader.android.lesson2n2.data.model.user.UserType.Companion.TEACHER
import kr.co.soundleader.android.lesson2n2.databinding.FragmentMainMyPageBinding
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.di.repository.UserRepository

import kr.co.soundleader.android.lesson2n2.ui.base.BaseFragment
import kr.co.soundleader.android.lesson2n2.ui.etc.activity.PresentActivity
import kr.co.soundleader.android.lesson2n2.ui.main.activity.MainActivity
import kr.co.soundleader.android.lesson2n2.ui.main.viewmodel.MainMyPageViewModel
import kr.co.soundleader.android.lesson2n2.ui.mypage.activity.MyPageCurriculumTeacherActivity
import kr.co.soundleader.android.lesson2n2.ui.mypage.activity.MyPageModifyActivity
import kr.co.soundleader.android.lesson2n2.ui.sign.dialog.NeedLoginDialog
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainMyPageFragment : BaseFragment<FragmentMainMyPageBinding, MainMyPageViewModel>() {
    override val viewModel: MainMyPageViewModel by viewModel()
    val dataRepo : DataRepository by inject()
    val userRepo : UserRepository by inject()


    override fun getLayoutId(): Int {
        return R.layout.fragment_main_my_page
    }
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)



    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel



        val mainAcitivity = (activity as MainActivity)
        mainAcitivity.binding.navTopMenu.ivTopMenuLogo.visibility = View.INVISIBLE
        mainAcitivity.binding.navTopMenu.tvTopMenuTitle.visibility = View.VISIBLE
        Log.d(TAG, "MainMyPageFragment :: onCreate() -> " + userRepo.userName)

        setLayoutFromUserLogin(mainAcitivity.viewModel.userLoginState.value!!)




        binding.layoutAsk.setOnClickListener {
            val needLoginDialog = NeedLoginDialog()
            needLoginDialog.show(context.supportFragmentManager, "NeedLoginDialog")
        }
        binding.layoutTermOfUse.setOnClickListener {
            dataRepo.presentFragmentPageTag = FRAGMENT_TERM_OF_USE
            val intent = Intent(context, PresentActivity::class.java)
            startActivity(intent)
        }
        binding.layoutPrivacy.setOnClickListener {
            dataRepo.presentFragmentPageTag = FRAGMENT_PRIVACY
            val intent = Intent(context, PresentActivity::class.java)
            startActivity(intent)

        }

        binding.btnMyPageModify.setOnClickListener {
            val userLoginType = userRepo.userLoginType
            Log.d(TAG, "btnMyPageModify :: userLoginType -> " + userLoginType)
            if(userLoginType != GUEST){
                when(userLoginType){


                    TEACHER -> {
                        dataRepo.myPageModifyFragmentPageTag = FRAGMENT_MY_PAGE_MODIFY_TEACHER_01
                    }
                    STUDENT -> {
                        dataRepo.myPageModifyFragmentPageTag = FRAGMENT_MY_PAGE_MODIFY_STUDENT_01
                    }

                }
                val intent = Intent(context, MyPageModifyActivity::class.java)
                startActivity(intent)
            }else{
                val needLoginDialog = NeedLoginDialog()
                needLoginDialog.show(context.supportFragmentManager, "NeedLoginDialog")
            }

        }

        binding.btnMyPageCurriculum.setOnClickListener {
            val intent = Intent(context, MyPageCurriculumTeacherActivity::class.java)
            startActivity(intent)



        }








    }

    fun setLayoutFromUserLogin(userLoginType:Boolean){
        if(userLoginType){
            binding.layoutUserLogged.visibility = View.VISIBLE
            binding.layoutNeedLogin.visibility = View.GONE
            binding.layoutLogout.visibility = View.VISIBLE
            binding.layoutLeave.visibility = View.VISIBLE
            binding.tvUserName.text = userRepo.userName
            binding.tvUserEmail.text = userRepo.userEmail
            when(userRepo.userLoginType){

                TEACHER -> {

                    binding.btnMyPageCurriculum.visibility = View.VISIBLE

                }
                STUDENT -> {

                    binding.btnMyPageCurriculum.visibility = View.GONE

                }

            }
        }else{
            binding.layoutUserLogged.visibility = View.GONE
            binding.layoutNeedLogin.visibility = View.VISIBLE
            binding.layoutLogout.visibility = View.GONE
            binding.layoutLeave.visibility = View.GONE
            binding.tvUserName.text = ""
            binding.tvUserEmail.text = ""
        }
    }
}
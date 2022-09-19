package kr.co.soundleader.android.lesson2n2.ui.sign.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_SIGN_UP_STUDENT_01
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_SIGN_UP_STUDENT_01_TITLE
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_SIGN_UP_TEACHER_01
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_SIGN_UP_TEACHER_01_TITLE
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_SIGN_UP_TEACHER_02
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_SIGN_UP_TEACHER_02_TITLE
import kr.co.soundleader.android.lesson2n2.databinding.ActivitySignUpBinding
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseActivity
import kr.co.soundleader.android.lesson2n2.ui.sign.fragment.SignUpStudent01Fragment
import kr.co.soundleader.android.lesson2n2.ui.sign.fragment.SignUpTeacher01Fragment
import kr.co.soundleader.android.lesson2n2.ui.sign.fragment.SignUpTeacher02Fragment
import kr.co.soundleader.android.lesson2n2.ui.sign.viewmodel.SignUpViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {
    override val viewModel: SignUpViewModel by viewModel()
    val dataRepo : DataRepository by inject()


    override fun getLayoutId(): Int {
        return R.layout.activity_sign_up
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        val FRAGMENT_PAGE_TAG: String = dataRepo.signUpFragmentPageTag+""
        setTopMenu()
        setFragmentPage(FRAGMENT_PAGE_TAG)


    }
    override fun setTopMenu(){

        binding.navTopMenu.ibtnBack.visibility = View.VISIBLE
        binding.navTopMenu.ibtnBack.setOnClickListener {
            onBackPressed()
        }
        binding.navTopMenu.ivTopMenuLogo.visibility = View.INVISIBLE
        binding.navTopMenu.tvTopMenuTitle.visibility = View.VISIBLE
    }

    fun setFragmentPage(pageTag: String) {

        dataRepo.signUpFragmentPageTag = pageTag
            var fragment: Fragment
        Log.d(TAG, "pageTag : " + pageTag)
            when (pageTag) {
                FRAGMENT_SIGN_UP_TEACHER_01 -> {
                    Log.d(TAG, "setFragmentPage FRAGMENT_SIGN_UP_TEACHER_01 IN")
                    binding.navTopMenu.tvTopMenuTitle.text = FRAGMENT_SIGN_UP_TEACHER_01_TITLE
                    fragment = SignUpTeacher01Fragment()


                }
                FRAGMENT_SIGN_UP_TEACHER_02 -> {
                    Log.d(TAG, "setFragmentPage FRAGMENT_SIGN_UP_TEACHER_02 IN")
                    binding.navTopMenu.tvTopMenuTitle.text = FRAGMENT_SIGN_UP_TEACHER_02_TITLE
                    fragment = SignUpTeacher02Fragment()
                }
                FRAGMENT_SIGN_UP_STUDENT_01 -> {
                    binding.navTopMenu.tvTopMenuTitle.text = FRAGMENT_SIGN_UP_STUDENT_01_TITLE
                    fragment = SignUpStudent01Fragment()
                }
                else -> {
                    binding.navTopMenu.tvTopMenuTitle.text = FRAGMENT_SIGN_UP_TEACHER_01_TITLE
                    fragment = SignUpTeacher01Fragment()
                }

            }
            setFragment(fragment, pageTag)



    }
    fun setFragment(fragment: Fragment, tag: String) {

        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()

        val fragment_01 = fm.findFragmentByTag(FRAGMENT_SIGN_UP_TEACHER_01)
        val fragment_02 = fm.findFragmentByTag(FRAGMENT_SIGN_UP_TEACHER_02)
        val fragment_03 = fm.findFragmentByTag(FRAGMENT_SIGN_UP_STUDENT_01)

        if (fm.findFragmentByTag(tag) == null) {

            //  binding.navTopMenu.tvTopMenuTitle.visibility = View.INVISIBLE
            //   binding.navTopMenu.ivTopMenuLogo.visibility = View.VISIBLE
            ft.add(R.id.layout_body, fragment, tag)
        }



        // Hide all Fragment
        if (fragment_01 != null) {
            ft.hide(fragment_01)
        }
        if (fragment_02 != null) {
            ft.hide(fragment_02)
        }
        if (fragment_03 != null) {
            ft.hide(fragment_03)
        }


        // Show  current Fragment
        if (tag == FRAGMENT_SIGN_UP_TEACHER_01) {
            if (fragment_01 != null) {


                ft.show(fragment_01)
            }
        }
        if (tag == FRAGMENT_SIGN_UP_TEACHER_02) {
            if (fragment_02 != null) {

                ft.show(fragment_02)
            }
        }
        if (tag == FRAGMENT_SIGN_UP_STUDENT_01) {
            if (fragment_03 != null) {


                ft.show(fragment_03)
            }
        }


        ft.commitAllowingStateLoss()


    }

    override fun onBackPressed() {
        Log.d(TAG, "dataRepo.signUpFragmentPageTag : " + dataRepo.signUpFragmentPageTag)
        when(dataRepo.signUpFragmentPageTag){
            FRAGMENT_SIGN_UP_TEACHER_01->{
                super.onBackPressed()
            }
            FRAGMENT_SIGN_UP_TEACHER_02->{
                Log.d(TAG, "22222222222222222222")
                setFragmentPage(FRAGMENT_SIGN_UP_TEACHER_01)
            }
            FRAGMENT_SIGN_UP_STUDENT_01->{
                super.onBackPressed()
            }
            else->{
                super.onBackPressed()
            }




        }

    }
}
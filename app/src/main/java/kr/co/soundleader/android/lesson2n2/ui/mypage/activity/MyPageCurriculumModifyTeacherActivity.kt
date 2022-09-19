package kr.co.soundleader.android.lesson2n2.ui.mypage.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.databinding.ActivityMyPageCurriculumModifyTeacherBinding
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseActivity
import kr.co.soundleader.android.lesson2n2.ui.mypage.fragment.MyPageCurriculumModifyTeacher01Fragment
import kr.co.soundleader.android.lesson2n2.ui.mypage.fragment.MyPageCurriculumModifyTeacher02Fragment

import kr.co.soundleader.android.lesson2n2.ui.mypage.viewmodel.MyPageCurriculumModifyTeacherSharedViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPageCurriculumModifyTeacherActivity : BaseActivity<ActivityMyPageCurriculumModifyTeacherBinding, MyPageCurriculumModifyTeacherSharedViewModel>() {
    override val viewModel: MyPageCurriculumModifyTeacherSharedViewModel by viewModel()
    val dataRepo : DataRepository by inject()

    override fun getLayoutId(): Int {
        return R.layout.activity_my_page_curriculum_modify_teacher
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        val FRAGMENT_PAGE_TAG: String = dataRepo.myPageCurriculumModifyTeacherFragmentPageTag+""
        Log.d(TAG, "FRAGMENT_PAGE_TAG : " + FRAGMENT_PAGE_TAG)
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

        dataRepo.myPageCurriculumModifyTeacherFragmentPageTag = pageTag
        var fragment: Fragment
        Log.d(TAG, "pageTag : " + pageTag)
        when (pageTag) {
            Constants.FRAGMENT_MY_PAGE_CURRICULUM_MODIFY_TEACHER_01 -> {
                Log.d(TAG, "setFragmentPage FRAGMENT_CURRICULUM_ADD_01 IN")
                binding.navTopMenu.tvTopMenuTitle.text = Constants.FRAGMENT_MY_PAGE_CURRICULUM_MODIFY_TEACHER_01_TITLE
                fragment = MyPageCurriculumModifyTeacher01Fragment()


            }
            Constants.FRAGMENT_MY_PAGE_CURRICULUM_MODIFY_TEACHER_02 -> {
                Log.d(TAG, "setFragmentPage FRAGMENT_CURRICULUM_ADD_02 IN")
                binding.navTopMenu.tvTopMenuTitle.text = Constants.FRAGMENT_MY_PAGE_CURRICULUM_MODIFY_TEACHER_02_TITLE
                fragment = MyPageCurriculumModifyTeacher02Fragment()
            }

            else -> {
                binding.navTopMenu.tvTopMenuTitle.text = Constants.FRAGMENT_MY_PAGE_CURRICULUM_MODIFY_TEACHER_01_TITLE
                fragment = MyPageCurriculumModifyTeacher01Fragment()
            }

        }
        setFragment(fragment, pageTag)



    }
    fun setFragment(fragment: Fragment, tag: String) {

        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()

        val fragment_01 = fm.findFragmentByTag(Constants.FRAGMENT_MY_PAGE_CURRICULUM_MODIFY_TEACHER_01)
        val fragment_02 = fm.findFragmentByTag(Constants.FRAGMENT_MY_PAGE_CURRICULUM_MODIFY_TEACHER_02)

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



        // Show  current Fragment
        if (tag == Constants.FRAGMENT_MY_PAGE_CURRICULUM_MODIFY_TEACHER_01) {
            if (fragment_01 != null) {


                ft.show(fragment_01)
            }
        }
        if (tag == Constants.FRAGMENT_MY_PAGE_CURRICULUM_MODIFY_TEACHER_02) {
            if (fragment_02 != null) {

                ft.show(fragment_02)
            }
        }



        ft.commitAllowingStateLoss()


    }

    override fun onBackPressed() {
        Log.d(TAG, "dataRepo.myPageCurriculumModifyTeacherFragmentPageTag : " + dataRepo.myPageCurriculumModifyTeacherFragmentPageTag)
        when(dataRepo.myPageCurriculumModifyTeacherFragmentPageTag){
            Constants.FRAGMENT_MY_PAGE_CURRICULUM_MODIFY_TEACHER_01 ->{
                super.onBackPressed()
            }
            Constants.FRAGMENT_MY_PAGE_CURRICULUM_MODIFY_TEACHER_02 ->{
                setFragmentPage(Constants.FRAGMENT_MY_PAGE_CURRICULUM_MODIFY_TEACHER_01)
            }

            else->{
                super.onBackPressed()
            }




        }

    }
}
package kr.co.soundleader.android.lesson2n2.ui.curriculum.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_CURRICULUM_ADD_01_TITLE
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_CURRICULUM_ADD_02
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_CURRICULUM_ADD_02_TITLE
import kr.co.soundleader.android.lesson2n2.databinding.ActivityCurriculumAddBinding
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseActivity
import kr.co.soundleader.android.lesson2n2.ui.curriculum.fragment.CurriculumAdd01Fragment
import kr.co.soundleader.android.lesson2n2.ui.curriculum.fragment.CurriculumAdd02Fragment
import kr.co.soundleader.android.lesson2n2.ui.curriculum.viewmodel.CurriculumAddViewModel
import kr.co.soundleader.android.lesson2n2.ui.main.fragment.MainMyPageFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurriculumAddActivity : BaseActivity<ActivityCurriculumAddBinding, CurriculumAddViewModel>() {
    override val viewModel: CurriculumAddViewModel by viewModel()
    val dataRepo : DataRepository by inject()

    override fun getLayoutId(): Int {
        return R.layout.activity_curriculum_add
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        val FRAGMENT_PAGE_TAG: String = dataRepo.curriculumAddFragmentPageTag+""
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

        dataRepo.curriculumAddFragmentPageTag = pageTag
        var fragment: Fragment
        Log.d(TAG, "pageTag : " + pageTag)
        when (pageTag) {
            Constants.FRAGMENT_CURRICULUM_ADD_01 -> {
                Log.d(TAG, "setFragmentPage FRAGMENT_CURRICULUM_ADD_01 IN")
                binding.navTopMenu.tvTopMenuTitle.text = FRAGMENT_CURRICULUM_ADD_01_TITLE
                fragment = CurriculumAdd01Fragment()


            }
            Constants.FRAGMENT_CURRICULUM_ADD_02 -> {
                Log.d(TAG, "setFragmentPage FRAGMENT_CURRICULUM_ADD_02 IN")
                binding.navTopMenu.tvTopMenuTitle.text = FRAGMENT_CURRICULUM_ADD_02_TITLE
                fragment = CurriculumAdd02Fragment()
            }

            else -> {
                binding.navTopMenu.tvTopMenuTitle.text = FRAGMENT_CURRICULUM_ADD_01_TITLE
                fragment = CurriculumAdd01Fragment()
            }

        }
        setFragment(fragment, pageTag)



    }
    fun setFragment(fragment: Fragment, tag: String) {

        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()

        val fragment_01 = fm.findFragmentByTag(Constants.FRAGMENT_CURRICULUM_ADD_01)
        val fragment_02 = fm.findFragmentByTag(Constants.FRAGMENT_CURRICULUM_ADD_02)

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
        if (tag == Constants.FRAGMENT_CURRICULUM_ADD_01) {
            if (fragment_01 != null) {


                ft.show(fragment_01)
            }
        }
        if (tag == Constants.FRAGMENT_CURRICULUM_ADD_02) {
            if (fragment_02 != null) {

                ft.show(fragment_02)
            }
        }



        ft.commitAllowingStateLoss()


    }

    override fun onBackPressed() {
        Log.d(TAG, "dataRepo.curriculumAddFragmentPageTag : " + dataRepo.curriculumAddFragmentPageTag)
        when(dataRepo.curriculumAddFragmentPageTag){
            Constants.FRAGMENT_CURRICULUM_ADD_01 ->{
                super.onBackPressed()
            }
            Constants.FRAGMENT_CURRICULUM_ADD_02 ->{
                val fm: FragmentManager = supportFragmentManager
                val fragment_02 = fm.findFragmentByTag(FRAGMENT_CURRICULUM_ADD_02)


                if (fragment_02 != null) {
                    val mFragment: CurriculumAdd02Fragment = (fragment_02 as CurriculumAdd02Fragment)
                    mFragment.youTubePlayer.pause()



                }
                setFragmentPage(Constants.FRAGMENT_CURRICULUM_ADD_01)
            }

            else->{
                super.onBackPressed()
            }




        }

    }

}
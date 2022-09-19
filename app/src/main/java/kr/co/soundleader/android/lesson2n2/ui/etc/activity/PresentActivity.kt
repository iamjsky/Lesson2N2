package kr.co.soundleader.android.lesson2n2.ui.etc.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_PRIVACY
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_PRIVACY_TITLE
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_TERM_OF_USE
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_TERM_OF_USE_TITLE
import kr.co.soundleader.android.lesson2n2.databinding.ActivityPresentBinding
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseActivity
import kr.co.soundleader.android.lesson2n2.ui.etc.fragment.PresentPrivacyFragment
import kr.co.soundleader.android.lesson2n2.ui.etc.fragment.PresentTermOfUseFragment

import kr.co.soundleader.android.lesson2n2.ui.etc.viewmodel.PresentViewModel

import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PresentActivity : BaseActivity<ActivityPresentBinding, PresentViewModel>() {
    override val viewModel: PresentViewModel by viewModel()
    val dataRepo : DataRepository by inject()

    override fun getLayoutId(): Int {
        return R.layout.activity_present
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        val FRAGMENT_PAGE_TAG: String = dataRepo.presentFragmentPageTag+""
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

        dataRepo.presentFragmentPageTag = pageTag
        var fragment: Fragment
        Log.d(TAG, "pageTag : " + pageTag)
        when (pageTag) {
            FRAGMENT_TERM_OF_USE -> {
                binding.navTopMenu.tvTopMenuTitle.text = FRAGMENT_TERM_OF_USE_TITLE
                fragment = PresentTermOfUseFragment()


            }
            FRAGMENT_PRIVACY -> {
                binding.navTopMenu.tvTopMenuTitle.text = FRAGMENT_PRIVACY_TITLE
                fragment = PresentPrivacyFragment()
            }

            else -> {
                binding.navTopMenu.tvTopMenuTitle.text = FRAGMENT_TERM_OF_USE_TITLE
                fragment = PresentTermOfUseFragment()
            }

        }
        setFragment(fragment, pageTag)



    }
    fun setFragment(fragment: Fragment, tag: String) {

        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()

        val fragment_01 = fm.findFragmentByTag(FRAGMENT_TERM_OF_USE)
        val fragment_02 = fm.findFragmentByTag(FRAGMENT_PRIVACY)

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
        if (tag == FRAGMENT_TERM_OF_USE) {
            if (fragment_01 != null) {


                ft.show(fragment_01)
            }
        }
        if (tag == FRAGMENT_PRIVACY) {
            if (fragment_02 != null) {

                ft.show(fragment_02)
            }
        }



        ft.commitAllowingStateLoss()


    }

    override fun onBackPressed() {
        when(dataRepo.presentFragmentPageTag){
            FRAGMENT_TERM_OF_USE ->{
                super.onBackPressed()
            }
            FRAGMENT_PRIVACY ->{
                super.onBackPressed()
            }
            else->{
                super.onBackPressed()
            }




        }

    }
}
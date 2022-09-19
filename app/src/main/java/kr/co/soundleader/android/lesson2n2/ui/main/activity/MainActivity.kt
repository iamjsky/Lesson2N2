package kr.co.soundleader.android.lesson2n2.ui.main.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.MENU_01_TITLE
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.MENU_02_TITLE
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.MENU_03_TITLE
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.MENU_04_TITLE
import kr.co.soundleader.android.lesson2n2.databinding.ActivityMainBinding
import kr.co.soundleader.android.lesson2n2.di.repository.UserRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseActivity
import kr.co.soundleader.android.lesson2n2.ui.main.fragment.MainClassRoomFragment
import kr.co.soundleader.android.lesson2n2.ui.main.fragment.MainCurriculumFragment
import kr.co.soundleader.android.lesson2n2.ui.main.fragment.MainMyPageFragment
import kr.co.soundleader.android.lesson2n2.ui.main.fragment.MainTeacherFragment
import kr.co.soundleader.android.lesson2n2.ui.main.viewmodel.MainViewModel
import kr.co.soundleader.android.lesson2n2.ui.sign.activity.SignInActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModel()
    val userRepo : UserRepository by inject()









    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")

        CoroutineScope(Dispatchers.Main).launch {

            val loginState = myDataStore.loginState.first()
            Log.d(TAG, "MAIN :: loginState -> " + loginState)

            viewModel.userLoginState.postValue(loginState)




        }







    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel



        setTopMenu()
        setBottomNav()

        Log.d(TAG, "ONCREATE")

        viewModel.userLoginState.observe(this, Observer {
            Log.d(TAG, "MAIN :: userLoginState.observe -> " + it)

            val fm: FragmentManager = supportFragmentManager
            val menu_04 = fm.findFragmentByTag(MENU_04_TITLE)


            if (menu_04 != null) {
                Log.d(TAG, "MAIN :: userLoginState.observe -> menu_04 IN -> " + it)
                val mFragment: MainMyPageFragment = (menu_04 as MainMyPageFragment)
                Log.d(TAG, "MAIN :: userLoginState.observe -> menu_04 IN -> " + userRepo.userName)
                mFragment.setLayoutFromUserLogin(it)



            }



        })



    }

    override fun setTopMenu(){
        //TEST
//        binding.navTopMenu.layoutTopMenuBody.setOnClickListener {
//            val intent = Intent(this, SignInActivity::class.java)
//            startActivity(intent)
//        }

        binding.navTopMenu.ivNavMenu.setOnClickListener {

            binding.layoutNavDrawer.openDrawer((GravityCompat.START))

        }

        binding.navTopMenu.ibtnBack.visibility = View.GONE
    }

    fun setBottomNav() {
        removeNavigationShiftMode(binding.navBottomMenu)
        binding.navBottomMenu.setOnItemSelectedListener { item ->
            //setTopMenuTitle(false)

            var fragmentTag: String = ""
            var fragment: Fragment

            when (item.itemId) {
                R.id.menu_01 -> {


                    binding.navBottomMenu.itemIconTintList =
                        ContextCompat.getColorStateList(this, R.color.color_bottom_menu_01)
                    binding.navBottomMenu.itemTextColor =
                        ContextCompat.getColorStateList(this, R.color.color_bottom_menu_01)
                    fragmentTag = MENU_01_TITLE
                    fragment = MainCurriculumFragment()


                }
                R.id.menu_02 -> {


                    binding.navBottomMenu.itemIconTintList =
                        ContextCompat.getColorStateList(this, R.color.color_bottom_menu_02)
                    binding.navBottomMenu.itemTextColor =
                        ContextCompat.getColorStateList(this, R.color.color_bottom_menu_02)
                    fragmentTag = MENU_02_TITLE
                    fragment = MainTeacherFragment()
                }
                R.id.menu_03 -> {

                    binding.navBottomMenu.itemIconTintList =
                        ContextCompat.getColorStateList(this, R.color.color_bottom_menu_03)
                    binding.navBottomMenu.itemTextColor =
                        ContextCompat.getColorStateList(this, R.color.color_bottom_menu_03)
                    fragmentTag = MENU_03_TITLE
                    fragment = MainClassRoomFragment()
                }
                R.id.menu_04 -> {

                    binding.navBottomMenu.itemIconTintList =
                        ContextCompat.getColorStateList(this, R.color.color_bottom_menu_04)
                    binding.navBottomMenu.itemTextColor =
                        ContextCompat.getColorStateList(this, R.color.color_bottom_menu_04)
                    fragmentTag = MENU_04_TITLE
                    fragment = MainMyPageFragment()
                }
                else -> {

                    binding.navBottomMenu.itemIconTintList =
                        ContextCompat.getColorStateList(this, R.color.color_bottom_menu_01)
                    binding.navBottomMenu.itemTextColor =
                        ContextCompat.getColorStateList(this, R.color.color_bottom_menu_01)
                    fragmentTag = Constants.MENU_01_TITLE
                    fragment = MainTeacherFragment()
                }

            }
            binding.navTopMenu.tvTopMenuTitle.text = fragmentTag + ""
            setFragment(fragment, fragmentTag)

            true

        }

        binding.navBottomMenu.selectedItemId = R.id.menu_01

    }


    private var backKeyPressadTime: Long = 0


    override fun onBackPressed() {
        if (binding.layoutNavDrawer.isDrawerOpen(GravityCompat.START)) {
            binding.layoutNavDrawer.closeDrawer(GravityCompat.START)
        } else {

            when(binding.navBottomMenu.selectedItemId){
                R.id.menu_01->{
                    Log.d(TAG, "binding.navBottomMenu.selectedItemId : " + binding.navBottomMenu.selectedItemId)
                    if(System.currentTimeMillis() - backKeyPressadTime >= 2000){
                        backKeyPressadTime = System.currentTimeMillis()
                        Toast.makeText(this, "뒤로 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
                        return
                    }else {
                        Log.d(TAG, "finish()")
                        finishAffinity()

                    }
                }else->{
                    super.onBackPressed()
                }
            }



        }

    }

    fun setFragment(fragment: Fragment, tag: String) {
        val fm: FragmentManager = supportFragmentManager


        val menu_01 = fm.findFragmentByTag(MENU_01_TITLE)
        val menu_02 = fm.findFragmentByTag(MENU_02_TITLE)
        val menu_03 = fm.findFragmentByTag(MENU_03_TITLE)
        val menu_04 = fm.findFragmentByTag(MENU_04_TITLE)
        val ft: FragmentTransaction = fm.beginTransaction()

        if (fm.findFragmentByTag(tag) == null) {

            Log.d(TAG, "MAIN :: setFragment() FIRST TIME -> " + fragment)
            ft.add(R.id.layout_body, fragment, tag)
        }



        // Hide all Fragment
        if (menu_01 != null) {
            (menu_01 as MainCurriculumFragment).binding.rvCurriculumList.suppressLayout(true)
            ft.hide(menu_01)
        }
        if (menu_02 != null) {
            (menu_02 as MainTeacherFragment).binding.rvTeacherList.suppressLayout(true)
            ft.hide(menu_02)
        }
        if (menu_03 != null) {
            ft.hide(menu_03)
        }
        if (menu_04 != null) {
            ft.hide(menu_04)
        }

        // Show  current Fragment
        if (tag == MENU_01_TITLE) {
            if (menu_01 != null) {
                binding.navTopMenu.tvTopMenuTitle.visibility = View.INVISIBLE
                binding.navTopMenu.ivTopMenuLogo.visibility = View.VISIBLE
                val mFragment: MainCurriculumFragment = (menu_01 as MainCurriculumFragment)
                if(mFragment.scrollPosY > mFragment.scrollPosYLimit){
                    binding.navTopMenu.ivTopMenuLogo.visibility = View.INVISIBLE
                    binding.navTopMenu.tvTopMenuTitle.visibility = View.VISIBLE
                }else{
                    binding.navTopMenu.tvTopMenuTitle.visibility = View.INVISIBLE
                    binding.navTopMenu.ivTopMenuLogo.visibility = View.VISIBLE
                }
                ft.show(menu_01)
            }
        }
        if (tag == MENU_02_TITLE) {
            if (menu_02 != null) {
                binding.navTopMenu.tvTopMenuTitle.visibility = View.INVISIBLE
                binding.navTopMenu.ivTopMenuLogo.visibility = View.VISIBLE
                val mFragment: MainTeacherFragment = (menu_02 as MainTeacherFragment)
                if(mFragment.scrollPosY > mFragment.scrollPosYLimit){
                    binding.navTopMenu.ivTopMenuLogo.visibility = View.INVISIBLE
                    binding.navTopMenu.tvTopMenuTitle.visibility = View.VISIBLE

                }else{
                    binding.navTopMenu.tvTopMenuTitle.visibility = View.INVISIBLE
                    binding.navTopMenu.ivTopMenuLogo.visibility = View.VISIBLE
                }
                ft.show(menu_02)
            }
        }
        if (tag == MENU_03_TITLE) {
            if (menu_03 != null) {
         binding.navTopMenu.ivTopMenuLogo.visibility = View.INVISIBLE
              binding.navTopMenu.tvTopMenuTitle.visibility = View.VISIBLE

                ft.show(menu_03)
            }
        }
        if (tag == MENU_04_TITLE) {
            if (menu_04 != null) {
                Log.d(TAG, "MAIN :: setFragment() menu_04 -> " + viewModel.userLoginState.value)

                binding.navTopMenu.ivTopMenuLogo.visibility = View.INVISIBLE
                binding.navTopMenu.tvTopMenuTitle.visibility = View.VISIBLE


                val mFragment: MainMyPageFragment = (menu_04 as MainMyPageFragment)
                Log.d(TAG, "MAIN :: setFragment() -> menu_04 -> " + userRepo.userName)
                mFragment.setLayoutFromUserLogin(viewModel.userLoginState.value!!)


                ft.show(menu_04)
            }
        }

        ft.commitAllowingStateLoss()


    }

    @SuppressLint("RestrictedApi")
    fun removeNavigationShiftMode(view: BottomNavigationView) {
        val menuView = view.getChildAt(0) as BottomNavigationMenuView
        menuView.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED
        menuView.buildMenuView()
    }

}
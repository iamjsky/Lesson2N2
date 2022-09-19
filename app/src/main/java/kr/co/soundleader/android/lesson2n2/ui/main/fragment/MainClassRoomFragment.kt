package kr.co.soundleader.android.lesson2n2.ui.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.databinding.FragmentMainClassRoomBinding

import kr.co.soundleader.android.lesson2n2.ui.base.BaseFragment
import kr.co.soundleader.android.lesson2n2.ui.main.activity.MainActivity
import kr.co.soundleader.android.lesson2n2.ui.main.viewmodel.MainClassRoomViewModel
import kr.co.soundleader.android.lesson2n2.ui.main.adapter.MainClassRoomTeacherViewPagerAdapter
import kr.co.soundleader.android.lesson2n2.ui.main.fragment.teacher.MainClassRoomEndTeacherFragment
import kr.co.soundleader.android.lesson2n2.ui.main.fragment.teacher.MainClassRoomMatchingTeacherFragment
import kr.co.soundleader.android.lesson2n2.ui.main.fragment.teacher.MainClassRoomStartTeacherFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainClassRoomFragment : BaseFragment<FragmentMainClassRoomBinding, MainClassRoomViewModel>() {
    override val viewModel: MainClassRoomViewModel by viewModel()
    private val tabTitleArray = arrayOf(
        "매칭중",
        "강의중",
        "강의종료"
    )


    override fun getLayoutId(): Int {
        return R.layout.fragment_main_class_room
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
        setViewPager()



    }

    fun setViewPager() {
        (activity as MainActivity).binding.navTopMenu.ivTopMenuLogo.visibility = View.INVISIBLE
        (activity as MainActivity).binding.navTopMenu.tvTopMenuTitle.visibility = View.VISIBLE
        val pagerAdapter = MainClassRoomTeacherViewPagerAdapter(this)
        // 3개의 Fragment Add
        pagerAdapter.addFragment(MainClassRoomMatchingTeacherFragment())
        pagerAdapter.addFragment(MainClassRoomStartTeacherFragment())
        pagerAdapter.addFragment(MainClassRoomEndTeacherFragment())
        // Adapter
        binding.vpPager.adapter = pagerAdapter

        binding.vpPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position + 1}")
            }
        })

        TabLayoutMediator(binding.layoutTab, binding.vpPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()

    }
}


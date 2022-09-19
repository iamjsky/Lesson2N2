package kr.co.soundleader.android.lesson2n2.ui.mypage.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.databinding.ActivityMyPageCurriculumTeacherBinding
import kr.co.soundleader.android.lesson2n2.ui.base.BaseActivity
import kr.co.soundleader.android.lesson2n2.ui.mypage.adapter.MyPageCurriculumTeacherListAdapter
import kr.co.soundleader.android.lesson2n2.ui.mypage.viewmodel.MyPageCurriculumTeacherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPageCurriculumTeacherActivity : BaseActivity<ActivityMyPageCurriculumTeacherBinding, MyPageCurriculumTeacherViewModel>() {
    override val viewModel: MyPageCurriculumTeacherViewModel by viewModel()

    override fun getLayoutId(): Int {
        return R.layout.activity_my_page_curriculum_teacher
    }

    lateinit var mypageCurriculumTeacherListAdapter: MyPageCurriculumTeacherListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        setTopMenu()
        mypageCurriculumTeacherListAdapter = MyPageCurriculumTeacherListAdapter(this)
        mypageCurriculumTeacherListAdapter.setHeaderVisible(false)
        binding.rvCurriculumList.layoutManager = LinearLayoutManager(this)
        binding.rvCurriculumList.setHasFixedSize(true)
        binding.rvCurriculumList.adapter = mypageCurriculumTeacherListAdapter

        binding.rvCurriculumList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.rvCurriculumList.canScrollVertically(1)) {
                    viewModel.getSongList()
                }


            }




        })



        viewModel.songListLiveData.observe(this, Observer {
            (binding.rvCurriculumList.adapter as MyPageCurriculumTeacherListAdapter).addData(it)

        })

        viewModel.getSongList()




    }
    override fun setTopMenu(){
        binding.navTopMenu.tvTopMenuTitle.text = Constants.ACTIVITY_MY_PAGE_CURRICULUM_TITLE
        binding.navTopMenu.ibtnBack.visibility = View.VISIBLE
        binding.navTopMenu.ibtnBack.setOnClickListener {
            onBackPressed()
        }

    }
}
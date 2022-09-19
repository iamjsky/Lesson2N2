package kr.co.soundleader.android.lesson2n2.ui.teacher.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.ACTIVITY_TEACHER_DETAIL_TITLE
import kr.co.soundleader.android.lesson2n2.databinding.ActivityTeacherDetailBinding
import kr.co.soundleader.android.lesson2n2.ui.base.BaseActivity
import kr.co.soundleader.android.lesson2n2.ui.main.adapter.MainCurriculumListAdapter
import kr.co.soundleader.android.lesson2n2.ui.teacher.viewmodel.TeacherDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TeacherDetailActivity : BaseActivity<ActivityTeacherDetailBinding, TeacherDetailViewModel>() {
    override val viewModel: TeacherDetailViewModel by viewModel()
    lateinit var mainCurriculumListAdapter: MainCurriculumListAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_teacher_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        setTopMenu()

        mainCurriculumListAdapter = MainCurriculumListAdapter(this)
        mainCurriculumListAdapter.setHeaderVisible(false)
        binding.rvCurriculumList.layoutManager = LinearLayoutManager(this)
        binding.rvCurriculumList.setHasFixedSize(true)
        binding.rvCurriculumList.adapter = mainCurriculumListAdapter

        binding.rvCurriculumList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.rvCurriculumList.canScrollVertically(1)) {
                    viewModel.getSongList()
                }


            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {


            }


        })



        viewModel.songListLiveData.observe(this, Observer {
            (binding.rvCurriculumList.adapter as MainCurriculumListAdapter).addData(it)

        })

        viewModel.getSongList()

    }
    override fun setTopMenu(){
        binding.navTopMenu.tvTopMenuTitle.text = ACTIVITY_TEACHER_DETAIL_TITLE
        binding.navTopMenu.ibtnBack.visibility = View.VISIBLE
        binding.navTopMenu.ibtnBack.setOnClickListener {
            onBackPressed()


        }
    }
}
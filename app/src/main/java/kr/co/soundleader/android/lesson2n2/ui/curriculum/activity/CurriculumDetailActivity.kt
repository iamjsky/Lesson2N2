package kr.co.soundleader.android.lesson2n2.ui.curriculum.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.ACTIVITY_CURRICULUM_DETAIL_TITLE
import kr.co.soundleader.android.lesson2n2.databinding.ActivityCurriculumDetailBinding
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseActivity
import kr.co.soundleader.android.lesson2n2.ui.curriculum.viewmodel.CurriculumDetailViewModel
import kr.co.soundleader.android.lesson2n2.ui.teacher.activity.TeacherDetailActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurriculumDetailActivity : BaseActivity<ActivityCurriculumDetailBinding, CurriculumDetailViewModel>() {
    override val viewModel: CurriculumDetailViewModel by viewModel()
    val dataRepo : DataRepository by inject()
    override fun getLayoutId(): Int {
        return R.layout.activity_curriculum_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        setTopMenu()
        val curriculumTitle:String = dataRepo.curriculumTeacherDetailData
        binding.tvCurriculumTitle.text = curriculumTitle

        binding.btnTeacherDetail.setOnClickListener {

            val intent = Intent(this, TeacherDetailActivity::class.java)
            startActivity(intent)


        }


    }
    override fun setTopMenu(){
        binding.navTopMenu.tvTopMenuTitle.text = ACTIVITY_CURRICULUM_DETAIL_TITLE
        binding.navTopMenu.ibtnBack.visibility = View.VISIBLE
        binding.navTopMenu.ibtnBack.setOnClickListener {
            onBackPressed()


        }
    }
}
package kr.co.soundleader.android.lesson2n2.ui.mypage.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.data.Constants.Companion.FRAGMENT_MY_PAGE_CURRICULUM_MODIFY_TEACHER_01
import kr.co.soundleader.android.lesson2n2.databinding.ActivityMyPageCurriculumDetailTeacherBinding
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseActivity
import kr.co.soundleader.android.lesson2n2.ui.curriculum.viewmodel.CurriculumDetailViewModel
import kr.co.soundleader.android.lesson2n2.ui.mypage.viewmodel.MyPageCurriculumDetailTeacherViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent.inject

class MyPageCurriculumDetailTeacherActivity : BaseActivity<ActivityMyPageCurriculumDetailTeacherBinding, MyPageCurriculumDetailTeacherViewModel>() {
    override val viewModel: MyPageCurriculumDetailTeacherViewModel by viewModel()
    val dataRepo : DataRepository by inject()
    override fun getLayoutId(): Int {
        return R.layout.activity_my_page_curriculum_detail_teacher
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        setTopMenu()
      //  val curriculumTitle:String = dataRepo.curriculumTeacherDetailData


        // 커리큘럼 수정 이동후 뒤로가기 눌렀을 경우 현재 페이지 내용 갱신 필요
        binding.btnCurriculumModify.setOnClickListener {
            dataRepo.myPageCurriculumModifyTeacherFragmentPageTag = FRAGMENT_MY_PAGE_CURRICULUM_MODIFY_TEACHER_01
            val intent = Intent(this, MyPageCurriculumModifyTeacherActivity::class.java)
            startActivity(intent)
        }


    }
    override fun setTopMenu(){
        binding.navTopMenu.tvTopMenuTitle.text = Constants.ACTIVITY_MY_PAGE_CURRICULUM_DETAIL_TITLE
        binding.navTopMenu.ibtnBack.visibility = View.VISIBLE
        binding.navTopMenu.ibtnBack.setOnClickListener {
            onBackPressed()


        }
    }
}
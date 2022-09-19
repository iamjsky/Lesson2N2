package kr.co.soundleader.android.lesson2n2.ui.mypage.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.databinding.FragmentMyPageModifyStudent01Binding
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository

import kr.co.soundleader.android.lesson2n2.ui.base.BaseFragment
import kr.co.soundleader.android.lesson2n2.ui.etc.activity.PresentActivity
import kr.co.soundleader.android.lesson2n2.ui.mypage.viewmodel.MyPageModifyStudentSharedViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MyPageModifyStudent01Fragment : BaseFragment<FragmentMyPageModifyStudent01Binding, MyPageModifyStudentSharedViewModel>() {
    override val viewModel: MyPageModifyStudentSharedViewModel by sharedViewModel()
    val dataRepo : DataRepository by inject()

    override fun getLayoutId(): Int {
        return R.layout.fragment_my_page_modify_student_01
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





    }
}
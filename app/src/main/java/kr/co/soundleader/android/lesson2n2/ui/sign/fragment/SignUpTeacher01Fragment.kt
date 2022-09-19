package kr.co.soundleader.android.lesson2n2.ui.sign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.databinding.FragmentSignUpTeacher01Binding

import kr.co.soundleader.android.lesson2n2.ui.base.BaseFragment
import kr.co.soundleader.android.lesson2n2.ui.sign.viewmodel.SignUpTeacherSharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SignUpTeacher01Fragment : BaseFragment<FragmentSignUpTeacher01Binding, SignUpTeacherSharedViewModel>() {
    override val viewModel: SignUpTeacherSharedViewModel by sharedViewModel()

    override fun getLayoutId(): Int {
        return R.layout.fragment_sign_up_teacher_01
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
package kr.co.soundleader.android.lesson2n2.ui.etc.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.databinding.FragmentCurriculumAdd01Binding
import kr.co.soundleader.android.lesson2n2.databinding.FragmentPresentPrivacyBinding
import kr.co.soundleader.android.lesson2n2.databinding.FragmentPresentTermOfUseBinding
import kr.co.soundleader.android.lesson2n2.databinding.FragmentSignUpTeacher01Binding

import kr.co.soundleader.android.lesson2n2.ui.base.BaseFragment
import kr.co.soundleader.android.lesson2n2.ui.curriculum.viewmodel.CurriculumAddSharedViewModel
import kr.co.soundleader.android.lesson2n2.ui.etc.viewmodel.PresentSharedViewModel
import kr.co.soundleader.android.lesson2n2.ui.sign.viewmodel.SignUpTeacherSharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PresentPrivacyFragment : BaseFragment<FragmentPresentPrivacyBinding, PresentSharedViewModel>() {
    override val viewModel: PresentSharedViewModel by sharedViewModel()

    override fun getLayoutId(): Int {
        return R.layout.fragment_present_privacy
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
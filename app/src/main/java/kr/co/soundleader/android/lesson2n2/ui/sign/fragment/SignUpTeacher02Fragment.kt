package kr.co.soundleader.android.lesson2n2.ui.sign.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.databinding.FragmentSignUpTeacher01Binding
import kr.co.soundleader.android.lesson2n2.databinding.FragmentSignUpTeacher02Binding
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository

import kr.co.soundleader.android.lesson2n2.ui.base.BaseFragment
import kr.co.soundleader.android.lesson2n2.ui.etc.activity.PresentActivity
import kr.co.soundleader.android.lesson2n2.ui.sign.viewmodel.SignUpTeacherSharedViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SignUpTeacher02Fragment : BaseFragment<FragmentSignUpTeacher02Binding, SignUpTeacherSharedViewModel>() {
    override val viewModel: SignUpTeacherSharedViewModel by sharedViewModel()
    val dataRepo : DataRepository by inject()

    override fun getLayoutId(): Int {
        return R.layout.fragment_sign_up_teacher_02
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

        binding.btnTermOfUse.setOnClickListener {
            dataRepo.presentFragmentPageTag = Constants.FRAGMENT_TERM_OF_USE
            val intent = Intent(context, PresentActivity::class.java)
            startActivity(intent)
        }
        binding.btnPrivacy.setOnClickListener {
            dataRepo.presentFragmentPageTag = Constants.FRAGMENT_PRIVACY
            val intent = Intent(context, PresentActivity::class.java)
            startActivity(intent)

        }



    }
}
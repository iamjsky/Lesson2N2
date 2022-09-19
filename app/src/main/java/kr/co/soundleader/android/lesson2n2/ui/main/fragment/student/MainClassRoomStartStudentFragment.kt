package kr.co.soundleader.android.lesson2n2.ui.main.fragment.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.databinding.FragmentMainClassRoomStartStudentBinding

import kr.co.soundleader.android.lesson2n2.ui.base.BaseFragment
import kr.co.soundleader.android.lesson2n2.ui.main.viewmodel.MainClassRoomStudentSharedViewModel
import kr.co.soundleader.android.lesson2n2.ui.main.viewmodel.MainClassRoomViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainClassRoomStartStudentFragment : BaseFragment<FragmentMainClassRoomStartStudentBinding, MainClassRoomStudentSharedViewModel>() {
    override val viewModel: MainClassRoomStudentSharedViewModel by viewModel()

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



    }
}
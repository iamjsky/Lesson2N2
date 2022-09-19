package kr.co.soundleader.android.lesson2n2.ui.main.fragment.teacher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.databinding.FragmentMainClassRoomMatchingTeacherBinding
import kr.co.soundleader.android.lesson2n2.di.repository.UserRepository

import kr.co.soundleader.android.lesson2n2.ui.base.BaseFragment
import kr.co.soundleader.android.lesson2n2.ui.main.adapter.MainClassRoomMatchingTeacherListAdapter
import kr.co.soundleader.android.lesson2n2.ui.main.viewmodel.MainClassRoomTeacherSharedViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainClassRoomMatchingTeacherFragment : BaseFragment<FragmentMainClassRoomMatchingTeacherBinding, MainClassRoomTeacherSharedViewModel>() {
    override val viewModel: MainClassRoomTeacherSharedViewModel by sharedViewModel()
    val userRepo : UserRepository by inject()

    lateinit var mainClassRoomMatchingTeacherListAdapter: MainClassRoomMatchingTeacherListAdapter


    override fun getLayoutId(): Int {
        return R.layout.fragment_main_class_room_matching_teacher
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

        mainClassRoomMatchingTeacherListAdapter = MainClassRoomMatchingTeacherListAdapter(context, userRepo)
        mainClassRoomMatchingTeacherListAdapter.setHeaderVisible(false)
        binding.rvMatchingListTeacher.layoutManager = LinearLayoutManager(context)
        binding.rvMatchingListTeacher.setHasFixedSize(true)
        binding.rvMatchingListTeacher.adapter = mainClassRoomMatchingTeacherListAdapter

        binding.rvMatchingListTeacher.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.rvMatchingListTeacher.canScrollVertically(1)) {
                    viewModel.getSongList1()
                }


            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {



            }


        })



        viewModel.songListLiveData1.observe(context, Observer {
            (binding.rvMatchingListTeacher.adapter as MainClassRoomMatchingTeacherListAdapter).addData(it)

        })

        viewModel.getSongList1()



    }
}
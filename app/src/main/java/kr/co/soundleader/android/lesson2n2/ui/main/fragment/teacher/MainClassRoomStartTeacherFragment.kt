package kr.co.soundleader.android.lesson2n2.ui.main.fragment.teacher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.databinding.FragmentMainClassRoomStartTeacherBinding

import kr.co.soundleader.android.lesson2n2.ui.base.BaseFragment
import kr.co.soundleader.android.lesson2n2.ui.main.adapter.MainClassRoomStartTeacherListAdapter
import kr.co.soundleader.android.lesson2n2.ui.main.viewmodel.MainClassRoomTeacherSharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainClassRoomStartTeacherFragment : BaseFragment<FragmentMainClassRoomStartTeacherBinding, MainClassRoomTeacherSharedViewModel>() {
    override val viewModel: MainClassRoomTeacherSharedViewModel by sharedViewModel()
    lateinit var mainClassRoomStartTeacherListAdapter: MainClassRoomStartTeacherListAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_class_room_start_teacher
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

        mainClassRoomStartTeacherListAdapter = MainClassRoomStartTeacherListAdapter(context)
        mainClassRoomStartTeacherListAdapter.setHeaderVisible(false)
        binding.rvStartListTeacher.layoutManager = LinearLayoutManager(context)
        binding.rvStartListTeacher.setHasFixedSize(true)
        binding.rvStartListTeacher.adapter = mainClassRoomStartTeacherListAdapter

        binding.rvStartListTeacher.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.rvStartListTeacher.canScrollVertically(1)) {
                    viewModel.getSongList2()
                }


            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {



            }


        })



        viewModel.songListLiveData2.observe(context, Observer {
            (binding.rvStartListTeacher.adapter as MainClassRoomStartTeacherListAdapter).addData(it)

        })

        viewModel.getSongList2()


    }
}
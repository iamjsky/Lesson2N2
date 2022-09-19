package kr.co.soundleader.android.lesson2n2.ui.main.fragment.teacher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.databinding.FragmentMainClassRoomEndTeacherBinding

import kr.co.soundleader.android.lesson2n2.ui.base.BaseFragment
import kr.co.soundleader.android.lesson2n2.ui.main.adapter.MainClassRoomEndTeacherListAdapter
import kr.co.soundleader.android.lesson2n2.ui.main.viewmodel.MainClassRoomTeacherSharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainClassRoomEndTeacherFragment : BaseFragment<FragmentMainClassRoomEndTeacherBinding, MainClassRoomTeacherSharedViewModel>() {
    override val viewModel: MainClassRoomTeacherSharedViewModel by sharedViewModel()
    lateinit var mainClassRoomEndTeacherListAdapter: MainClassRoomEndTeacherListAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_class_room_end_teacher
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

        mainClassRoomEndTeacherListAdapter = MainClassRoomEndTeacherListAdapter(context)
        mainClassRoomEndTeacherListAdapter.setHeaderVisible(false)
        binding.rvEndListTeacher.layoutManager = LinearLayoutManager(context)
        binding.rvEndListTeacher.setHasFixedSize(true)
        binding.rvEndListTeacher.adapter = mainClassRoomEndTeacherListAdapter

        binding.rvEndListTeacher.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.rvEndListTeacher.canScrollVertically(1)) {
                    viewModel.getSongList3()
                }


            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {



            }


        })



        viewModel.songListLiveData3.observe(context, Observer {
            (binding.rvEndListTeacher.adapter as MainClassRoomEndTeacherListAdapter).addData(it)

        })

        viewModel.getSongList3()


    }
}
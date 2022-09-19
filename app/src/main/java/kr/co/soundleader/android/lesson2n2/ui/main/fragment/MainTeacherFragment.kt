package kr.co.soundleader.android.lesson2n2.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.databinding.FragmentMainTeacherBinding

import kr.co.soundleader.android.lesson2n2.ui.base.BaseFragment
import kr.co.soundleader.android.lesson2n2.ui.main.activity.MainActivity
import kr.co.soundleader.android.lesson2n2.ui.main.adapter.MainTeacherListAdapter
import kr.co.soundleader.android.lesson2n2.ui.main.viewmodel.MainTeacherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainTeacherFragment : BaseFragment<FragmentMainTeacherBinding, MainTeacherViewModel>() {
    override val viewModel: MainTeacherViewModel by viewModel()

    lateinit var mainTeacherListAdapter: MainTeacherListAdapter


    override fun getLayoutId(): Int {
        return R.layout.fragment_main_teacher
    }

    val scrollPosYLimit: Int = 200
    var scrollPosY: Int = 0

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(hidden){
            binding.rvTeacherList.suppressLayout(true)
        }else{

            binding.rvTeacherList.suppressLayout(false)
        }



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

        mainTeacherListAdapter = MainTeacherListAdapter(context)
        binding.rvTeacherList.layoutManager = LinearLayoutManager(context)
        binding.rvTeacherList.setHasFixedSize(true)
        binding.rvTeacherList.adapter = mainTeacherListAdapter

        binding.rvTeacherList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.rvTeacherList.canScrollVertically(1)) {
                    viewModel.getSongList()
                }


            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                scrollPosY += dy

                if(scrollPosY > scrollPosYLimit){
                    (activity as MainActivity).binding.navTopMenu.ivTopMenuLogo.visibility = View.INVISIBLE
                    (activity as MainActivity).binding.navTopMenu.tvTopMenuTitle.visibility = View.VISIBLE

                }else{
                    (activity as MainActivity).binding.navTopMenu.tvTopMenuTitle.visibility = View.INVISIBLE
                    (activity as MainActivity).binding.navTopMenu.ivTopMenuLogo.visibility = View.VISIBLE
                }

            }


        })



        viewModel.songListLiveData.observe(context, Observer {
            (binding.rvTeacherList.adapter as MainTeacherListAdapter).addData(it)

        })

        viewModel.getSongList()



        //test
//        binding.ivMoveTest.setOnClickListener {
//
//            (activity as MainActivity).binding.navBottomMenu.selectedItemId = R.id.menu_02
//
//        }

    }
}
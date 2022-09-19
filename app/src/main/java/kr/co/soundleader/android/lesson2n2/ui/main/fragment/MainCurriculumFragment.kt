package kr.co.soundleader.android.lesson2n2.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.databinding.FragmentMainCurriculumBinding
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseFragment
import kr.co.soundleader.android.lesson2n2.ui.curriculum.activity.CurriculumAddActivity
import kr.co.soundleader.android.lesson2n2.ui.main.activity.MainActivity
import kr.co.soundleader.android.lesson2n2.ui.main.adapter.MainCurriculumListAdapter
import kr.co.soundleader.android.lesson2n2.ui.main.viewmodel.MainCurriculumViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainCurriculumFragment: BaseFragment<FragmentMainCurriculumBinding, MainCurriculumViewModel>() {
    override val viewModel: MainCurriculumViewModel by viewModel()
    val dataRepo : DataRepository by inject()

    lateinit var mainCurriculumListAdapter: MainCurriculumListAdapter


    override fun getLayoutId(): Int {
        return R.layout.fragment_main_curriculum
    }

    val scrollPosYLimit: Int = 200
    var scrollPosY: Int = 0

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(hidden){
            binding.rvCurriculumList.suppressLayout(true)
        }else{
            binding.rvCurriculumList.suppressLayout(false)
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

        mainCurriculumListAdapter = MainCurriculumListAdapter(context)
        mainCurriculumListAdapter.setHeaderVisible(true)
        binding.rvCurriculumList.layoutManager = LinearLayoutManager(context)
        binding.rvCurriculumList.setHasFixedSize(true)
        binding.rvCurriculumList.adapter = mainCurriculumListAdapter

        binding.rvCurriculumList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.rvCurriculumList.canScrollVertically(1)) {
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
            (binding.rvCurriculumList.adapter as MainCurriculumListAdapter).addData(it)

        })

        viewModel.getSongList()

        binding.fabAddCurriculum.setOnClickListener {
            dataRepo.curriculumAddFragmentPageTag = Constants.FRAGMENT_CURRICULUM_ADD_01
            val intent = Intent(context, CurriculumAddActivity::class.java)
            context.startActivity(intent)


        }

    }
}
package kr.co.soundleader.android.lesson2n2.ui.rvtest

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.databinding.ActivityRecyclerViewBinding
import kr.co.soundleader.android.lesson2n2.ui.rvtest.RecyclerViewViewModel
import kr.co.soundleader.android.lesson2n2.ui.base.BaseActivity

import org.koin.android.scope.newScope
import org.koin.androidx.viewmodel.ext.android.viewModel



class RecyclerViewActivity : BaseActivity<ActivityRecyclerViewBinding, RecyclerViewViewModel>() {
    override val viewModel: RecyclerViewViewModel by viewModel()

    lateinit var songListAdapter: SongListAdapter


    override fun getLayoutId(): Int {
        return R.layout.activity_recycler_view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        songListAdapter = SongListAdapter(this)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.setHasFixedSize(true)
        binding.rvList.adapter = songListAdapter

        binding.rvList.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!binding.rvList.canScrollVertically(1)){
                    viewModel.getSongList()
                }
            }
        })


        viewModel.songListLiveData.observe(this, Observer {
            (binding.rvList.adapter as SongListAdapter).addData(it)

        })



    }




}
package kr.co.soundleader.android.lesson2n2.ui.curriculum.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.databinding.FragmentCurriculumAdd01Binding
import kr.co.soundleader.android.lesson2n2.databinding.FragmentCurriculumAdd02Binding
import kr.co.soundleader.android.lesson2n2.databinding.FragmentSignUpTeacher01Binding

import kr.co.soundleader.android.lesson2n2.ui.base.BaseFragment
import kr.co.soundleader.android.lesson2n2.ui.curriculum.viewmodel.CurriculumAddSharedViewModel
import kr.co.soundleader.android.lesson2n2.ui.sign.viewmodel.SignUpTeacherSharedViewModel
import kr.co.soundleader.android.lesson2n2.utils.YoutubeUtil
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CurriculumAdd02Fragment : BaseFragment<FragmentCurriculumAdd02Binding, CurriculumAddSharedViewModel>() {
    override val viewModel: CurriculumAddSharedViewModel by sharedViewModel()

    override fun getLayoutId(): Int {
        return R.layout.fragment_curriculum_add_02
    }


    lateinit var youTubePlayer: YouTubePlayer


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
        binding.playerYoutube.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
            override fun onYouTubePlayer(player: YouTubePlayer) {
                Log.d(TAG,"onYouTubePlayer :: ")
                youTubePlayer = player
            }
        })



        viewModel.youTubeUrl.observe(context, Observer {

            Log.d(TAG,"youTubeUrl :: " + it.toString())
            val youTubeId = YoutubeUtil.getVideoId(it.toString())
            youTubePlayer.cueVideo(youTubeId, 0f)

        })



    }
}
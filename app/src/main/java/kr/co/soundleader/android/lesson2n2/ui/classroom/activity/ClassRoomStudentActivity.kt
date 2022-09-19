package kr.co.soundleader.android.lesson2n2.ui.classroom.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.data.Constants
import kr.co.soundleader.android.lesson2n2.data.model.api.ArticleList
import kr.co.soundleader.android.lesson2n2.databinding.ActivityClassRoomStudentBinding
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseActivity
import kr.co.soundleader.android.lesson2n2.ui.classroom.adapter.ClassRoomTeacherChatListAdapter
import kr.co.soundleader.android.lesson2n2.ui.classroom.viewmodel.ClassRoomStudentViewModel
import kr.co.soundleader.android.lesson2n2.utils.SoftKeyboardDetector
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClassRoomStudentActivity : BaseActivity<ActivityClassRoomStudentBinding, ClassRoomStudentViewModel>() {
    override val viewModel: ClassRoomStudentViewModel by viewModel()
    val dataRepo : DataRepository by inject()
    override fun getLayoutId(): Int {
        return R.layout.activity_class_room_student
    }
    lateinit var classRoomTeacherCharListAdapter: ClassRoomTeacherChatListAdapter

    override fun onResume() {
        super.onResume()

    }

    var testCount = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTopMenu()

        val softKeyboardDetector = SoftKeyboardDetector(this)
        addContentView(softKeyboardDetector, FrameLayout.LayoutParams(-1, -1))

        softKeyboardDetector.setOnShownKeyboard(object :
            SoftKeyboardDetector.OnShownKeyboardListener {
            override fun onShowSoftKeyboard() {
                binding.layoutFoot.visibility = View.INVISIBLE
            }
        })

        softKeyboardDetector.setOnHiddenKeyboard(object :
            SoftKeyboardDetector.OnHiddenKeyboardListener {
            override fun onHiddenSoftKeyboard() {
                binding.layoutFoot.visibility = View.VISIBLE
            }
        })

        classRoomTeacherCharListAdapter = ClassRoomTeacherChatListAdapter(this)
        classRoomTeacherCharListAdapter.setHeaderVisible(false)
        binding.rvChat.layoutManager = LinearLayoutManager(this)
        binding.rvChat.setHasFixedSize(true)
        binding.rvChat.adapter = classRoomTeacherCharListAdapter
        binding.rvChat.layoutManager = LinearLayoutManager(this).apply {
            this.stackFromEnd = true    // 가장 최근의 대화를 표시하기 위해 맨 아래로 정렬.
        }




        binding.rvChat.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.rvChat.canScrollVertically(1)) {
                    viewModel.getSongList()

                }


            }


        })



        viewModel.songListLiveData.observe(this, Observer {
            (binding.rvChat.adapter as ClassRoomTeacherChatListAdapter).addData(it)
            binding.rvChat.scrollToPosition(classRoomTeacherCharListAdapter.itemCount-1)

        })

        viewModel.getSongList()



        binding.btnSend.setOnClickListener {
            val articleList = ArticleList()
            testCount++
            articleList.idx = testCount.toString()
            articleList.title = "test_" + binding.etChat.text.toString()

            val arrayList = ArrayList<ArticleList>()
            arrayList.add(articleList)
            classRoomTeacherCharListAdapter.addData(arrayList)
            binding.rvChat.scrollToPosition(classRoomTeacherCharListAdapter.itemCount-1)

        }


    }


    override fun setTopMenu() {
        binding.navTopMenu.tvTopMenuTitle.text = Constants.ACTIVITY_CLASS_ROOM_STUDENT_TITLE
        binding.navTopMenu.ibtnBack.visibility = View.VISIBLE
        binding.navTopMenu.ibtnBack.setOnClickListener {
            onBackPressed()


        }
    }
}
package kr.co.soundleader.android.lesson2n2.ui.classroom.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.databinding.ActivityClassRoomStudentBinding
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.ui.base.BaseActivity
import kr.co.soundleader.android.lesson2n2.ui.classroom.viewmodel.ClassRoomStudentViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClassRoomStudentActivity : BaseActivity<ActivityClassRoomStudentBinding, ClassRoomStudentViewModel>() {
    override val viewModel: ClassRoomStudentViewModel by viewModel()
    val dataRepo : DataRepository by inject()
    override fun getLayoutId(): Int {
        return R.layout.activity_class_room_student
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





    }
}
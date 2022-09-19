package kr.co.soundleader.android.lesson2n2.di

import kr.co.soundleader.android.lesson2n2.ui.base.TopMenuViewModel
import kr.co.soundleader.android.lesson2n2.ui.classroom.viewmodel.ClassRoomStudentViewModel
import kr.co.soundleader.android.lesson2n2.ui.classroom.viewmodel.ClassRoomTeacherViewModel
import kr.co.soundleader.android.lesson2n2.ui.curriculum.viewmodel.CurriculumAddSharedViewModel
import kr.co.soundleader.android.lesson2n2.ui.curriculum.viewmodel.CurriculumAddViewModel
import kr.co.soundleader.android.lesson2n2.ui.curriculum.viewmodel.CurriculumDetailViewModel
import kr.co.soundleader.android.lesson2n2.ui.etc.viewmodel.PresentSharedViewModel
import kr.co.soundleader.android.lesson2n2.ui.etc.viewmodel.PresentViewModel
import kr.co.soundleader.android.lesson2n2.ui.main.viewmodel.*
import kr.co.soundleader.android.lesson2n2.ui.mypage.viewmodel.*
import kr.co.soundleader.android.lesson2n2.ui.rvtest.RecyclerViewViewModel
import kr.co.soundleader.android.lesson2n2.ui.sign.viewmodel.*
import kr.co.soundleader.android.lesson2n2.ui.splash.SplashViewModel
import kr.co.soundleader.android.lesson2n2.ui.teacher.viewmodel.TeacherDetailViewModel
import kr.co.soundleader.android.lesson2n2.ui.test.Sel1ViewModel
import kr.co.soundleader.android.lesson2n2.ui.test.Sel2ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get(), get()) }
    viewModel { RecyclerViewViewModel(get()) }
    viewModel { Sel1ViewModel(get()) }
    viewModel { Sel2ViewModel(get()) }
    viewModel { MainViewModel(get(), get()) }
    viewModel { MainTeacherViewModel(get()) }
    viewModel { MainClassRoomViewModel(get()) }
    viewModel { MainCurriculumViewModel(get()) }
    viewModel { SignInViewModel(get(),get(), get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { SignUpStudentSharedViewModel(get(), get()) }
    viewModel { SignUpTeacherSharedViewModel(get(),get()) }
    viewModel { TopMenuViewModel(get()) }
    viewModel { SignDialogSharedViewModel(get()) }
    viewModel { MainMyPageViewModel(get(), get()) }
    viewModel { MainClassRoomTeacherSharedViewModel(get()) }
    viewModel { MainClassRoomStudentSharedViewModel(get()) }
    viewModel { TeacherDetailViewModel(get(), get()) }
    viewModel { CurriculumDetailViewModel(get(), get()) }
    viewModel { CurriculumAddViewModel(get(), get()) }
    viewModel { CurriculumAddSharedViewModel(get(), get()) }
    viewModel { PresentViewModel(get()) }
    viewModel { PresentSharedViewModel(get()) }
    viewModel { MyPageModifyViewModel(get(), get()) }
    viewModel { MyPageModifyTeacherSharedViewModel(get(), get()) }
    viewModel { MyPageModifyStudentSharedViewModel(get(), get()) }
    viewModel { MyPageCurriculumTeacherViewModel(get(), get()) }
    viewModel { MyPageCurriculumDetailTeacherViewModel(get(), get()) }
    viewModel { MyPageCurriculumModifyTeacherViewModel(get(), get()) }
    viewModel { MyPageCurriculumModifyTeacherSharedViewModel(get(), get()) }
    viewModel { ClassRoomTeacherViewModel(get(), get()) }
    viewModel { ClassRoomStudentViewModel(get(), get()) }

}
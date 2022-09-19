package kr.co.soundleader.android.lesson2n2.di

import kr.co.soundleader.android.lesson2n2.di.repository.ApiRepository
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.di.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ApiRepository(get()) }
    single { DataRepository(get()) }
    single { UserRepository(get()) }
}
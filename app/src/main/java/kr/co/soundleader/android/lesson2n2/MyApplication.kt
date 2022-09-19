package kr.co.soundleader.android.lesson2n2

import android.app.Application
import kr.co.soundleader.android.lesson2n2.data.MyDataStore
import kr.co.soundleader.android.lesson2n2.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication: Application() {


    private lateinit var dataStore : MyDataStore
    companion object {
        private lateinit var myApplication: MyApplication
        fun getInstance() : MyApplication = myApplication
    }
    override fun onCreate() {
        super.onCreate()
        startKoin() {
            androidContext(this@MyApplication)
            modules(appModule)
        }
        myApplication = this
        dataStore = MyDataStore(this)
    }
    fun getDataStore() : MyDataStore = dataStore

}
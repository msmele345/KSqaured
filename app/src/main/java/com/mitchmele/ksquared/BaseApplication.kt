package com.mitchmele.ksquared

import android.app.Application
import com.mitchmele.ksquared.repository.network.AlgorithmKoinServiceApi
import com.mitchmele.ksquared.repository.network.AlgorithmRepository
import com.mitchmele.ksquared.ui.AlgorithmViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApplication : Application() {


    val listOfModules = module {
        single { AlgorithmKoinServiceApi() }
        single { AlgorithmRepository() }
        viewModel { AlgorithmViewModel(get()) }
    }


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOfModules)
        }
    }
}
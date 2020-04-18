package com.mitchmele.ksquared

import android.app.Application
import com.mitchmele.ksquared.repository.network.AlgorithmApi
import com.mitchmele.ksquared.repository.network.AlgorithmRepository
import com.mitchmele.ksquared.ui.AlgorithmDetailViewModel
import com.mitchmele.ksquared.ui.AlgorithmViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class BaseApplication : Application() {

    val listOfModules = module {
        viewModel { AlgorithmViewModel(get()) }
        viewModel { AlgorithmDetailViewModel(get()) }
    }

    val apiModule = module {
        fun providesApiService(retrofit: Retrofit): AlgorithmApi =
            retrofit
                .create(AlgorithmApi::class.java)

        single { providesApiService(get()) } //dependencies don't have to be in same module
    }

    val repositoryModule = module {
        factory { AlgorithmRepository(get()) }
    }

    val retroFitModule = module {
        fun provideMoshi(): MoshiConverterFactory {
            return MoshiConverterFactory.create()
        }

        fun provideHttpClient(): OkHttpClient {
            return OkHttpClient().newBuilder()
                .run {
                    connectTimeout(10, TimeUnit.SECONDS)
                    writeTimeout(10, TimeUnit.SECONDS)
                    readTimeout(20, TimeUnit.SECONDS)
                    build()
                }
        }

        fun provideRetrofit(factory: MoshiConverterFactory, client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(factory)
                .client(client)
                .build()
        }

        single { provideMoshi() }
        single { provideHttpClient() }
        single { provideRetrofit(get(), get()) }

    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(listOfModules, apiModule, retroFitModule, repositoryModule))
        }
    }
}


package com.mitchmele.ksquared.repository.network

import com.mitchmele.ksquared.model.Algorithm
import com.mitchmele.ksquared.model.MoshiUUIDAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "http://10.0.2.2:8080/algorithms/all/"
const val NAME_URL = "http://10.0.2.2:8080/algorithms/{name}/"

object ApiFactory {


    //pass api to Repository constructor
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }



    private val algoClient: OkHttpClient = OkHttpClient().newBuilder()
        .run {
            connectTimeout(10, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)
            readTimeout(20, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
            build()
        }


    private fun retroFit(url: String): Retrofit {
        return Retrofit.Builder()
            .client(algoClient)
            .baseUrl(url)
            .addConverterFactory(
                MoshiConverterFactory.create().asLenient()
            )
            .build()

    }

    val algoApi: AlgorithmApi =
        retroFit(BASE_URL)
            .create(AlgorithmApi::class.java)


    //option 2
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(AlgorithmApi::class.java)
    }
}


/*
    Creating Auth Interceptor to add api_key query in front of all the requests.
    private val authInterceptor = Interceptor {chain->
            val newUrl = chain.request().url()
                    .newBuilder()
                    .addQueryParameter("api_key", AppConstants.tmdbApiKey)
                    .build()

            val newRequest = chain.request()
                    .newBuilder()
                    .url(newUrl)
                    .build()

            chain.proceed(newRequest)
        }
* */
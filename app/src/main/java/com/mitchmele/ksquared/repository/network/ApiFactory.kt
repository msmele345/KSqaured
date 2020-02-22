package com.mitchmele.ksquared.repository.network

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "http://10.0.2.2:8080/algorithms/all/"
object ApiFactory {


    //pass api to Repository constructor
    private val algoClient: OkHttpClient = OkHttpClient().newBuilder().build()

    fun retroFit(): Retrofit {
        return Retrofit.Builder()
            .client(algoClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    }


    val algoApi: AlgorithmApi = retroFit().create(AlgorithmApi::class.java)

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
package com.jaax.twitchretrofit.network

import com.jaax.twitchretrofit.util.Consts
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Consts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        //addCallAdapterFactory para que retrofit identifique quien es el observable
    }

    fun getService(): TwitchService {
        return getRetrofit().create(TwitchService::class.java)
    }
}
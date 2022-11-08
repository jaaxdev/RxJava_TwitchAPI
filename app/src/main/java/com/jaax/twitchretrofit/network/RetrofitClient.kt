package com.jaax.twitchretrofit.network

import com.jaax.twitchretrofit.util.Consts
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Consts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService(): TwitchService {
        return getRetrofit().create(TwitchService::class.java)
    }
}
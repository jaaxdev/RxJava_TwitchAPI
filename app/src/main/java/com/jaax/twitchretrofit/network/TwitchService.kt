package com.jaax.twitchretrofit.network

import com.jaax.twitchretrofit.model.TopGame
import com.jaax.twitchretrofit.util.Consts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface TwitchService {
    @Headers(
        "Authorization: Bearer ${Consts.TOKEN}",
        "Client-Id: ${Consts.CLIENT_ID}"
    )
    @GET("top")
    fun getTopGames(): Call<TopGame>
}
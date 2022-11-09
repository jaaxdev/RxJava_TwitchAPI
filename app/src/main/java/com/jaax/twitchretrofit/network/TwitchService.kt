package com.jaax.twitchretrofit.network

import com.jaax.twitchretrofit.model.TopGames
import com.jaax.twitchretrofit.util.Consts
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface TwitchService {
    @Headers(
        "Authorization: Bearer ${Consts.TOKEN}",
        "Client-Id: ${Consts.CLIENT_ID}"
    )
    @GET("top")
    fun getTopGames(): Call<TopGames>


    @Headers(
        "Authorization: Bearer ${Consts.TOKEN}",
        "Client-Id: ${Consts.CLIENT_ID}"
    )
    @GET("top")
    fun getTopGamesObservable(): Observable<TopGames>
}
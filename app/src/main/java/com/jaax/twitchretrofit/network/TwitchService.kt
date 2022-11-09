package com.jaax.twitchretrofit.network

import com.jaax.twitchretrofit.model.AllStreams
import com.jaax.twitchretrofit.model.TopGames
import com.jaax.twitchretrofit.util.Consts
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TwitchService {
    @Headers(
        "Authorization: Bearer ${Consts.TOKEN}",
        "Client-Id: ${Consts.CLIENT_ID}"
    )
    @GET("games/top")
    fun getTopGames(): Call<TopGames>


    @Headers(
        "Authorization: Bearer ${Consts.TOKEN}",
        "Client-Id: ${Consts.CLIENT_ID}"
    )
    @GET("games/top")
    fun getTopGamesObservable(): Observable<TopGames>

    @Headers(
        "Authorization: Bearer ${Consts.TOKEN}",
        "Client-Id: ${Consts.CLIENT_ID}"
    )
    @GET("streams")
    fun getStreamsObservable(): Observable<AllStreams>
}
package com.jaax.twitchretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jaax.twitchretrofit.model.AllStreams
import com.jaax.twitchretrofit.model.Stream
import com.jaax.twitchretrofit.model.TopGames
import com.jaax.twitchretrofit.network.RetrofitClient
import com.jaax.twitchretrofit.util.Consts
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Predicate
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        //getResponse()
        getStreams()
    }

    private fun getResponse() {

        /*val call = RetrofitClient.getService().getTopGames()
        call.enqueue(object : Callback<TopGames> {
            override fun onResponse(call: Call<TopGames>, response: Response<TopGames>) {
                if(response.isSuccessful) {
                    Log.i(Consts.TAG, response.body()!!.games[0].name)
                } else {
                    Log.i(Consts.TAG, "No success")
                }
            }

            override fun onFailure(call: Call<TopGames>, t: Throwable) {
                Log.e(Consts.TAG, "Error ${t.printStackTrace()}")
            }

        })*/

        RetrofitClient.getService().getTopGamesObservable()

            //fromIterable -> se obtiene uno o más datos a partir de un conjunto
            .flatMap { twitch -> Observable.fromIterable(twitch.games) }
            // just -> se obtiene sólo un dato
            .flatMap { game -> Observable.just(game.name) }
            .filter { game -> game.toString().lowercase().contains("w") }
            .subscribeOn( //indica en qué hilo se va a ejecutar la suscripción
                Schedulers.io()) //al ser llamada de tip IO, se ejecuta en este hilo
            .observeOn(AndroidSchedulers.mainThread()) //el resultado se va a pintar sobre la UI
            .subscribe(object : Observer<String> {
                override fun onSubscribe(disposable: Disposable) {
                    Log.i(Consts.TAG_RX, "onSubscribe")
                }

                override fun onNext(name: String) {
                    Log.i(Consts.TAG_RX, "onNext -> GAME: $name")
                }

                override fun onError(e: Throwable) {
                    Log.i(Consts.TAG_RX, e.toString())
                }

                override fun onComplete() {
                    Log.i(Consts.TAG_RX, "onComplete")
                }

            })
    }

    private fun getStreams() {
        RetrofitClient.getService().getStreamsObservable()
            .flatMap { twitch -> Observable.fromIterable(twitch.streams) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Stream>{
                override fun onSubscribe(disposable: Disposable) {
                    Log.i(Consts.TAG_RX, "onSubscribe")
                }

                override fun onNext(stream: Stream) {
                    if(stream.viewer_count > 10000 && stream.language == "es") {
                        Log.i(Consts.TAG_RX, "onNext: \n" +
                                "GAME: ${stream.game_name}\n" +
                                "VIEWERS: ${stream.viewer_count}\n" +
                                "LANGUAGE: ${stream.language}")
                    }
                }

                override fun onError(e: Throwable) {
                    Log.i(Consts.TAG_RX, e.toString())
                }

                override fun onComplete() {
                    Log.i(Consts.TAG_RX, "onComplete")
                }

            })
    }
}